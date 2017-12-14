localStorage.setItem("tokens","aaa");
$.ajax({
	url: "/goc-web/file/list?pageindex=0&pagesize=100",
	type:"get",
	async:true,
	success: function(data){
		var datas = data.items;
		console.log(datas);
		
		$.each(datas,function(i,item){
			$('.uls').append("<li>"+item.fileName+"   "+item.resNum+"</li>");
		});
		
		return datas;
	}
});





var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles', 
    //runtimes : 'flash',
	container: document.getElementById('container'),
	flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
    url : 'http://goc6test.oss-cn-beijing.aliyuncs.com',
    multipart_params: {
		//'Filename': '${filename}', 
        'key' : '',
		'policy': '',
        'OSSAccessKeyId': '', 
        'success_action_status' : '', //让服务端返回200,不然，默认会返回204
		'signature': '',
	}
});

uploader.bind('FilesAdded',function(up, files){
	plupload.each(files, function(file) {
		document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
		+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
		+'</div>';
	});
});
uploader.bind('PostInit',function(){
	
});
uploader.bind('UploadProgress',function(up, file) {
	var d = document.getElementById(file.id);
	d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
    
    var prog = d.getElementsByTagName('div')[0];
	var progBar = prog.getElementsByTagName('div')[0]
	progBar.style.width= 2*file.percent+'px';
	progBar.setAttribute('aria-valuenow', file.percent);
});
uploader.bind('FileUploaded',function(up, file, info) {
    //alert(info.status)
    if (info.status >= 200 || info.status < 200)
    {
        document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'success';
    }
    else
    {
        document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
    } 
});
uploader.bind('Error',function(up, err) {
	document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
});
uploader.init();

document.getElementById('ossfile').innerHTML = '';
document.getElementById('postfiles').onclick = function() {
	var sss ;
	$.ajax({
		url: "/goc-web/policy?tokens="+localStorage.getItem("tokens"),
		type:"get",
		async:false,
		success: function(data){
			sss = jQuery.parseJSON(data);
			return data;
		}
	});

	var signature = sss.signature;
	var host = sss.host;
	var accessid = sss.accessid;
	var policyBase64 = sss.policy;//Base64.encode(JSON.stringify(policyText))
	var fileName = sss.dir;

	var names = "";
	console.log($('#ossfile').text().split(' ')[0]);
	$.ajax({
        url : "/goc-web/file/upload/",
        type : "POST",
        async: false,
        contentType: "application/json;charset=utf-8",
        data : JSON.stringify({'fileName':$('#ossfile').text().split(' ')[0],
        	"titles":$('#titles').val(),
        	"tokens":localStorage.getItem("tokens"),
        	"desc":$('#desc').val()}),
        dataType : "text",
        success : function(result) {
        	console.log("成功");
        	console.log(result);
        	names = result;
        	return result;
        },
        error:function(msg){
        	console.log(msg);
        }
    })
	console.log("Name:"+names);
	uploader.settings.multipart_params.key = fileName+'/'+names;
	uploader.settings.multipart_params.policy = policyBase64;
	uploader.settings.multipart_params.OSSAccessKeyId = accessid;
	uploader.settings.multipart_params.success_action_status = '200';
	uploader.settings.multipart_params.signature = signature;
	
	/*
	multipart_params: {
		//'Filename': '${filename}', 
        'key' : fileName+'/'+'${filename}',
		'policy': policyBase64,
        'OSSAccessKeyId': accessid, 
        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
		'signature': signature,
	}*/
	uploader.start();
	return false;
};

$("#downLoads").click(function(){
	var nums = $("#nums").val();
	$.ajax({
        type: "get",
        url:"/goc-web/downgoc?fileNum="+nums+"&tokens="+localStorage.getItem("tokens"),
        async: false,
        error: function(request) {
        },
        success: function(data) {
        	console.log("打印数据看下");
        	console.log(data);
        	$("#downUrl").attr("href",data).html("点击这里下载哦");
        	window.location.href=data;
        }
    });
	console.log(nums);
});
