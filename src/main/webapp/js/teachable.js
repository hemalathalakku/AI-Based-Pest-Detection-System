```javascript
/* =========================================================
   PestGuard AI - Teachable Machine Integration
   ========================================================= */

// New 11-class Teachable Machine model
const MODEL_URL = "https://teachablemachine.withgoogle.com/models/SKHEyfMAp/";

// Minimum confidence threshold: 75%
const MIN_CONFIDENCE = 0.75;

let model = null;


// =========================================================
// Load AI Model
// =========================================================

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


// =========================================================
// Preview Selected Image
// =========================================================

function onFileChosen(input) {

    const file = input.files[0];

    if (!file) return;

    const reader = new FileReader();

    reader.onload = function(e) {

        const img = document.getElementById("preview");

        img.onload = function() {
            document.getElementById("status").textContent = "";
        };

        img.src = e.target.result;
        img.style.display = "block";
    };

    reader.readAsDataURL(file);

    document.getElementById("predBox").style.display = "none";

    document.getElementById("saveBtn").disabled = true;

    document.getElementById("pestName").value = "";
    document.getElementById("confidence").value = "";

    document.getElementById("predLabel").textContent = "";
    document.getElementById("predConf").textContent = "";
}


// =========================================================
// Detect Pest
// =========================================================

async function detectPest() {

    const img = document.getElementById("preview");

    if (!img.src || !img.complete || !img.naturalWidth) {

        alert("Please choose a valid image first.");

        return;
    }

    const saveBtn = document.getElementById("saveBtn");

    const predLabel = document.getElementById("predLabel");
    const predConf = document.getElementById("predConf");

    saveBtn.disabled = true;

    document.getElementById("status").textContent =
        "Analyzing image...";

    try {

        // Load model
        await loadModel();

        // Get predictions
        const predictions = await model.predict(img);

        if (!predictions || predictions.length === 0) {
            throw new Error("No predictions received.");
        }

        // Find highest probability prediction
        let best = predictions[0];

        for (const p of predictions) {

            if (p.probability > best.probability) {
                best = p;
            }
        }

        const pct = (best.probability * 100).toFixed(2);

        document.getElementById("predBox").style.display = "block";


        // =================================================
        // Reject Invalid Images
        // =================================================

        if (best.className.trim().toLowerCase() === "invalid image") {

            predLabel.textContent =
                "Invalid Image. Please upload a plant leaf image.";

            predConf.textContent = "N/A";

            document.getElementById("pestName").value = "";
            document.getElementById("confidence").value = "";

            document.getElementById("status").textContent =
                "Image rejected. Please upload a valid leaf image.";

            return;
        }


        // =================================================
        // Reject Low Confidence Predictions
        // =================================================

        if (best.probability < MIN_CONFIDENCE) {

            predLabel.textContent =
                "Unable to identify. Please upload a clear leaf image.";

            predConf.textContent = "N/A";

            document.getElementById("pestName").value = "";
            document.getElementById("confidence").value = "";

            document.getElementById("status").textContent =
                "Image rejected. Please try another image.";

            return;
        }


        // =================================================
        // Accept Valid Prediction
        // =================================================

        predLabel.textContent = best.className;

        predConf.textContent = pct + "%";

        document.getElementById("pestName").value =
            best.className;

        document.getElementById("confidence").value = pct;

        saveBtn.disabled = false;

        document.getElementById("status").textContent =
            "Prediction completed successfully.";

    }

    catch (err) {

        console.error("PestGuard AI Error:", err);

        model = null;

        document.getElementById("predBox").style.display = "block";

        predLabel.textContent =
            "Unable to analyze image. Please try again.";

        predConf.textContent = "N/A";

        document.getElementById("pestName").value = "";
        document.getElementById("confidence").value = "";

        saveBtn.disabled = true;

        document.getElementById("status").textContent =
            "Could not load the AI model. Please try again.";
    }
}
```
