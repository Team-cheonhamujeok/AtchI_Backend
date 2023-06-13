# app.py
from urllib import request
from flask import Flask, render_template,request,jsonify

#Flask 객체 인스턴스 생성
app = Flask(__name__)

@app.route('/predict', methods=['POST']) # 접속하는 url
def index():
    response=request.get_json()
    print(type(response))
    return "hello"
    # if request.me == 'POST':
        
      
#   return render_template('index.html')

if __name__=="__main__":
  app.run(pdebug=True)
  # host 등을 직접 지정하고 싶다면
  # app.run(host="127.0.0.1", port="5000", debug=True)


  