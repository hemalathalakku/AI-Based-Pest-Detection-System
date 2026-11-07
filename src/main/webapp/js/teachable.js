/* =========================================================
   PestGuard AI - Teachable Machine Integration
   ========================================================= */

const MODEL_URL = "https://teachablemachine.withgoogle.com/models/mbYcQ3oo3/";

const MIN_CONFIDENCE = 0.75;

let model = null;


// Load AI model
async function loadModel() {

    if (model) return;

    document.getElementById("status").textContent =
        "Loading AI model...";

    model = await tmImage.load(
        MODEL_URL + "model.json",
        MODEL_URL + "metadata.json"
    );

    document.getElementById("status").textContent = "";
}


// Preview selected image
function onFileChosen(input) {

    const file = input.files[0];

    if (!file) return;

    const reader = new FileReader();

    reader.onload = function(e) {

        const img = document.getElementById("preview");

        img.src = e.target.result;
        img.style.display = "block";
    };

    reader.readAsDataURL(file);

    document.getElementById("predBox").style.display = "none";

    document.getElementById("saveBtn").disabled = true;

    document.getElementById("pestName").value = "";
    document.getElementById("confidence").value = "";
}


// Detect pest
async function detectPest() {

    const img = document.getElementById("preview");

    if (!img.src || !img.complete || !img.naturalWidth) {

        alert("Please choose a valid image first.");

        return;
    }

    const saveBtn = document.getElementById("saveBtn");

    saveBtn.disabled = true;

    try {

        await loadModel();

        const predictions = await model.predict(img);

        let best = predictions[0];

        for (const p of predictions) {

            if (p.probability > best.probability) {

                best = p;
            }
        }

        const pct = (best.probability * 100).toFixed(2);

        const predLabel = document.getElementById("predLabel");
        const predConf = document.getElementById("predConf");

        document.getElementById("predBox").style.display = "block";

        // Reject low-confidence predictions
        if (best.probability < MIN_CONFIDENCE) {

            predLabel.textContent =
                "Unable to identify. Please upload a clear leaf image.";

            predConf.textContent = pct + "%";

            document.getElementById("pestName").value = "";
            document.getElementById("confidence").value = "";

            document.getElementById("status").textContent =
                "Image rejected. Please try another image.";

            return;
        }

        // Accept prediction
        predLabel.textContent = best.className;

        predConf.textContent = pct + "%";

        document.getElementById("pestName").value =
            best.className;

        document.getElementById("confidence").value = pct;

        saveBtn.disabled = false;

        document.getElementById("status").textContent =
            "Prediction completed successfully.";

    } catch (err) {

        document.getElementById("status").textContent =
            "Could not load the AI model. Please try again.";

        console.error(err);
    }
}
