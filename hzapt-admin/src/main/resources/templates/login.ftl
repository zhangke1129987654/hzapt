<html>
<head>
    <title>杭州奥佩特健康管理有限公司</title>
    <script type="text/javascript">
        function checkForm() {
            var name = document.getElementById("model.name").value;
            var t = document.getElementById("model.gender");
            var gender=t.options[t.selectedIndex].value;
            var age = document.getElementById("model.age").value;
            var phoneNumber = document.getElementById("model.phoneNumber").value;
            var city = document.getElementById("model.city").value;
            if (!name|| name === '') {
                alert('姓名不能为空')
                return false
            }
            if(!gender || gender === ''){
                alert('性别不能为空')
                return false
            }
            if (!age|| age === '') {
                alert('年龄不能为空')
                return false
            }
            if (!phoneNumber|| phoneNumber === '') {
                alert('手机号不能为空')
                return false
            }
            if (!city|| city === '') {
                alert('城市不能为空')
                return false
            }
            const data = {
                name: name,
                age: age,
                gender:gender,
                phoneNumber:phoneNumber,
                city:city
            }
            let xhr = new XMLHttpRequest()
            //xhr.open('POST', 'http://localhost:8850/saveForm', true)
            xhr.open('POST', 'http://118.178.229.8:8850/saveForm', true)
            const str = JSON.stringify(data)
            xhr.setRequestHeader('Content-type', 'application/json')
            xhr.send(str)
            alert("登记成功");
            // xhr.addEventListener('load', function () {
            //     console.log(this.response)
            // })测试
        }
    </script>
    <style lang="scss" scoped>
        .login {
            width: 100vw;
            height: 100vh;
            background-size: 100% 100%;
            background-image: url("/beijing.jpg");
            background-repeat: no-repeat;
            position: absolute;
        }
        .content {
            width: 80%;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .head {
            color: #ffffff;
            margin-bottom: 50px;
        }
        .head_image{
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 30px 0;
        }
        .head_title{
            font-size: 45px;
            font-weight: 500;
            line-height: 75px;
            letter-spacing: 1.23px;
            padding: 0px 0px 50px 80px;
        }
        .form-wrapper {
            padding: 0 30px;
        }
        .form-item {
            padding: 0;
            margin-bottom: 30px;
            position: relative;
        }
        .title {
            margin:20px;
            font-size: 30px;
            color: #FFFFFF;
            width: 160px;
            padding: 10px 0;
        }
        .input {
            margin:20px;
            line-height: 28px;
            width: 100%;
            height: 85px;
            background: #eeeded;
            border-radius: 15px;
            position: relative;
            display: flex;
            align-items: center;
        }
       input {
          border-width: 0;
          background: #eeeded;
          padding-left: 40px;
          width: 100%;
          height: 54px;
          font-size: 30px;
          line-height: 54px;
          }
        .ph {
           color: #919191;
        }
        .submit-button {
            margin-top: 20px;
            letter-spacing: 10px;
            font-weight: 400;
            text-align: center;
            padding:0 180px;
        }
        button {
            margin-bottom: 40px;
            background: #448aca;
            border-radius: 40px;
            width: 380px;
            color: #ffffff;
            font-size: 40px;
            height: 80px;
            line-height: 80px;
        }
        .option {
            padding-left: 30px;
            font-size: 10px;
            color:#919191;
        }
        .select {
            width:100%;
            border-width: 0;
            padding-left: 40px;
            font-size: 30px;
            color:#919191;
            height: 70px;
            margin:20px;
            background: #eeeded;
            border-radius: 15px;
        }
    </style>
</head>

<body>
<view class="login">
    <view class="content">
        <!-- 头部 -->
        <view class="head">
            <view class="head_image">
                <image src="/title.jpg" mode="aspectFill" style="width: 90px;height: 90px;"></image>
            </view>
            <view class="head_title">杭州奥佩特健康管理有限公司</view>
        </view>

        <!-- 表单 -->
        <view class="form-wrapper">
            <form class="form" id = "form" onsubmit="save()">
                <view class="form-item">
                    <view class="title">姓名</view>
                    <view class="input">
                        <input id="model.name" type="text" placeholder="请输入姓名" placeholder-style="color:#919191;">
                    </view>
                </view>
                <view class="form-item">
                    <view class="title">年龄</view>
                    <view class="input">
                        <input id="model.age" maxlength="2" type="number" placeholder="请输入年龄" placeholder-style="color:#919191;">
                    </view>
                </view>
                <view class="form-item">
                    <view class="title">性别</view>
                    <select class="select" id = "model.gender" >
                        <option class="option" value="" >请输入性别</option>
                        <option class="option">男</option>
                        <option class="option">女</option>
                    </select>
                </view>
                <view class="form-item">
                    <view class="title">手机号</view>
                    <view class="input">
                        <input id="model.phoneNumber" maxlength="11" type="number" placeholder="请输入手机号" placeholder-style="color:#919191;">
                    </view>
                </view>
                <view class="form-item">
                    <view class="title">城市</view>
                    <view class="input">
                        <input id="model.city" type="text" placeholder="请输入城市" placeholder-style="color:#919191;">
                    </view>
                </view>
            </form>
        </view>

        <!-- 登录按钮 -->
        <view class="submit-button">
            <button onclick="checkForm()">确认</button>
        </view>
    </view>
</view>
</body>

</html>