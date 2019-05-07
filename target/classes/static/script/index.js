$(function(){
    initbooklist();
})

function tiaojianchaxun(){
    $('#bookTable').bootstrapTable('refresh')

}
function initbooklist(){


        $('#bookTable').bootstrapTable({
        toolbar:'#toolbar',
        url:"../qureyResume",
        type:"post",
        pagination:true, //是否展示分页
        pageList:[3, 10, 20, 50],//分页组件
        pageNumber:1,
        pageSize:3,//默认每页条数
        sidePagination:'server',//分页方式：client客户端分页，server服务端分页
        striped:true,//显示条纹状表格
        queryParams:function(){
            var boname = $("#namea").val();
            var mlogin = $("#login").val();
            return {
                page:this.pageNumber,
                rows:this.pageSize,
                namea:boname,
                login:mlogin,
            }
        },
        columns:[
            {field:'login',title:'手机号'},
            {field:'password',title:'密码'},

            {field:'namea',title:'用户姓名'},
            {field:'jiaxidname',title:'家乡市'},
            {field:'jiaoxidname',title:'家乡省'},

            {field:'xingb',title:'性别',

                formatter: function(value,row,index){
                    return value==1?"男":value==2?"女":value==3?"人妖":"";
                }},
            {field:'aihao',title:'爱好',
                formatter: function(value,row,index){
                    return value==1?"游泳":value==2?"机车":value==3?"看书":value==4?"打球":value==5?"慢跑":value==6?"极限":value==7?"交流":value==8?"玩":"";
                }},
            {title:'教育',field:'jiaoyidname',},
            {field:'hengyidname',title:'行业'},
            {field:'shuozaiidname',title:'所在城市'},
            {field:'shuosiname',title:'所在城市'},


            {field:'123',formatter:function(value,row,index){
                    return '<a href="javascript:shanchu('+row.id+');">删除</a>'
                }}
        ]

    });
}











function shanchu(id){
    bootbox.confirm({
        size:'small',
        message:"你确认要删除吗",
        buttons:{
            confirm:{
                label:'确认',
                className:'btn-success'
            },
            cancel:{
                label:'取消',
                className: 'btn-danger'
            }
        },
        callback:function(result){
            if(result){
                $.ajax({
                    url:"../deleteuser",
                    type:'post',
                    data:{
                        id:id
                    },
                    dataType:'json',
                    success:function(data){
                        if(data){
                            tiaojianchaxun();
                        }else{
                            bootbox.alert({
                                size:"small",
                                title:"提示",
                                message:"删除失败",
                            });
                        }

                    }

                })
            }
        }
    })

}





