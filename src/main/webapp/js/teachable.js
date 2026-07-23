/* =====================================================================
   Google Teachable Machine integration (runs in the browser).

   HOW TO GET YOUR MODEL:
   1. Go to https://teachablemachine.withgoogle.com/  ->  Image Project.
   2. Create one class per pest. IMPORTANT: name each class EXACTLY the
      same as a "pest_name" value in your MySQL "pest" table
      (e.g. "Aphids", "Leaf Blight", "Healthy Leaf").
   3. Train, then click "Export Model" -> "Upload (shareable link)".
   4. Copy the model URL it gives you and paste it below.
   ===================================================================== */
const MODEL_URL = "https://teachablemachine.withgoogle.com/models/mbYcQ3oo3/";

let model = null;

async function loadModel() {
  if (model) return;
  document.getElementById("status").textContent = "Loading AI model...";
  model = await tmImage.load(MODEL_URL + "model.json", MODEL_URL + "metadata.json");
  document.getElementById("status").textContent = "";
}

// Show a preview as soon as the farmer picks an image
function onFileChosen(input) {
  const file = input.files[0];
  if (!file) return;
  const reader = new FileReader();
  reader.onload = e => {
    const img = document.getElementById("preview");
    img.src = e.target.result;
    img.style.display = "block";
  };
  reader.readAsDataURL(file);
  document.getElementById("predBox").style.display = "none";
  document.getElementById("saveBtn").disabled = true;
}

// Run the model on the previewed image
async function detectPest() {
  const img = document.getElementById("preview");
  if (!img.src) { alert("Please choose an image first."); return; }
  try {
    await loadModel();
    if (!img.complete) { await new Promise(r => (img.onload = r)); }

    const predictions = await model.predict(img);
    // pick the class with the highest probability
    let best = predictions[0];
    for (const p of predictions) if (p.probability > best.probability) best = p;

    const pct = (best.probability * 100).toFixed(2);
    document.getElementById("predLabel").textContent = best.className;
    document.getElementById("predConf").textContent = pct + "%";
    document.getElementById("predBox").style.display = "block";

    // fill the hidden fields that get posted to the servlet
    document.getElementById("pestName").value = best.className;
    document.getElementById("confidence").value = pct;
    document.getElementById("saveBtn").disabled = false;
  } catch (err) {
    document.getElementById("status").textContent =
      "Could not load the model. Did you paste your Teachable Machine URL in js/teachable.js?";
    console.error(err);
  }
}
