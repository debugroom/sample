document.addEventListener('DOMContentLoaded', function(){
	var foo = document.getElementById('foo');
	foo.onclick = function toggleStyle(){
		this.className = (this.className === 'foo-before') 
							? 'foo-after' : 'foo-before';
	};
	var bar = document.getElementById('bar');
	bar.onclick = function toggleStyle(){
		this.classList.toggle('bar-after');
		this.classList.toggle('bar-before');
	};
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			if(xhr.status == 200){
				console.log(xhr.responseText);
			}
		}
	}
	xhr.open('GET', 'http://localhost:8080/sample-javaee6-web/resource/users');
	xhr.setRequestHeader('If-Modified-Since', 'Thu, 01 Jun 1970 00:00:00 GMT');
	xhr.send(null);

}, false)