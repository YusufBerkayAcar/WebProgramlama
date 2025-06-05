from flask import Flask, request, jsonify
from flask_cors import CORS
import requests
import os

from dotenv import load_dotenv
load_dotenv()

app = Flask(__name__)
CORS(app)

# Hugging Face API Bilgileri
API_URL = "https://api-inference.huggingface.co/models/savasy/bert-base-turkish-squad"

API_KEY = os.getenv("HF_API_KEY")
headers = {"Authorization": f"Bearer {API_KEY}"}

# Bilgi bağlamı
CONTEXT = """
Kullanıcı bu uygulamada:
- Etkinlik oluşturabilir: "create event" butonuyla.
- Etkinliğe katılabilir: "find event" ile uygun etkinliği bulup.
- Kayıt olabilir: Login ekranından "register" butonuyla.
- Harita üzerinden katıldığı etkinlikleri görebilir.
- Chat üzerinden diğer katılımcılarla iletişim kurabilir.
"""

@app.route("/ask", methods=["POST"])
def ask():
    try:
        data = request.get_json()
        question = data.get("question", "").strip()

        if not question:
            return jsonify({"answer": "Lütfen geçerli bir soru giriniz."})

        payload = {
            "inputs": {
                "question": question,
                "context": CONTEXT
            }
        }

        response = requests.post(API_URL, headers=headers, json=payload)

        if response.status_code == 200:
            result = response.json()
            answer = result.get("answer", "Bir cevap bulunamadı.")
            return jsonify({"answer": answer})
        else:
            return jsonify({"answer": f"Model hatası ({response.status_code}) oluştu. Lütfen tekrar deneyin."})

    except Exception as e:
        return jsonify({"answer": f"Sunucu hatası: {str(e)}"})

if __name__ == "__main__":
    app.run(debug=True)
