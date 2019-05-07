//注冊賬號
function addLog() {
    alert(1)
    $("#loginForm").form("submit",{
        url:"../savelogin",
            success:function(data){
                if (data==null||data==""){
                   alert("注冊失敗")
                    window.location.href="../page/zhuc"
                }else{
                    alert("注冊成功")
                    window.location.href="../page/login"
                }
            }
    })
}



