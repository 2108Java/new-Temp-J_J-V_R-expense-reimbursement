/**
 * 
 */
 
 
let buttonLogin = document.getElementById("login-button");
buttonLogin.addEventListener('click',login());

function login(){
    let username = document.getElementById("username-login").value;
    let password = document.getElementById("password-login").value;

    fetch("http://localhost:8000/loginEmployee", {//check if URL is correct
        mehod: 'POST',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
            },
            body: JSON.stringify({
                'username': username,
                'password': password
            })
    })
}

let manbuttonLogin = document.getElementById("man-login-button");
manbuttonLogin.addEventListener('click', manlogin_submit_form());
function manlogin_submit_form(){
    let username = document.getElementById("man-username-login").value;
    let password = document.getElementById("man-password-login").value;

    fetch("http://localhost:8000/loginFinanceManager", {//check if URL is correct
        mehod: 'POST',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
            },
            body: JSON.stringify({
                'username': username,
                'password': password
            })
    })
}

let buttonCreate = document.getElementById("create-acc-btn");
buttonCreate.addEventListener('click',createAccount());

function createAccount(){
    let firstname = document.getElementById("first-name-acc").value;
    let lastname = document.getElementById("last-name-acc").value;
    let title = document.getElementById("username-acc").value;
    let email = document.getElementById("email-acc").value;

    const tempPass = Math.random().toString(36);
    console.log(result);

    //create username
    str1 = firstname.substring(0, 1);
    usernameGen = str1 + lastname;


    fetch("http://localhost:8000/createAccount", {//check if URL is correct
        mehod: 'POST',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
            },
            body: JSON.stringify({
                'first_name': firstname,
                'last_name': lastname,
                'title': title,
                'email': email,
                'username': usernameGen,
                'tempPassword': tempPass 
            })
    })

    
    Email.send({
      Host: "smtp.gmail.com",//create dummy gmail account
      Username: "sender@email_address.com",
      Password: "Enter your password",
      To: email,
      From: "sender@email_address.com",
      Subject: "Sending Email using javascript",
      Body: "Please use the username and temporary password below to login" + "\nUsername:" +usernameGen + "\nTemporary Password" + tempPass,
    })
      .then(function (message) {
        alert("mail sent successfully")
      });
} 

//let buttonNewPassword = document.getElementById("forgot-pass-btn");
//buttonNewPassword.addEventListener('click',forgotPassword());


let resetBtn = document.getElementById("forgot-pass-btn");
resetBtn.addEventListener('click',reset_submit());

function reset_submit(){
    let newPassword = document.getElementById("newpassword").value;
    let username = document.getElementById("reset-username").value;
    

    fetch("http://localhost:8000/resetPassword", {//check if URL is correct
        mehod: 'POST',
        headers: {
            'Content-type': 'application/json',
            'Accept': 'application/json'
            },
            body: JSON.stringify({
                'username': username,//ask Vishaun about having this or removing this
                'newPassword': newPassword
            })
    })

}

function empType(){
    let employeeType;
    let radio = document.getElementsByClassName("form-check");
    for(i = 0; i < radio.length; i++) {
        if(radio[i].checked)
        employeeType = radio[i].value;
        if(employeeType == "Finance Manager"){
            location.replace("http://localhost:8000/loginFinanceManager")
        }else{
            location.replace("http://localhost:8000/loginEmployee")
        }

    }
        

}

