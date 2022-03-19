function previewImage() {
			var file = document.getElementById("file").files;
			if (file.length > 0) {
				var fileReader = new FileReader();

				fileReader.onload = function(event) {
					document.getElementById("preview").setAttribute("src",
							event.target.result);
				};

				fileReader.readAsDataURL(file[0]);
			}
		}
		
		
		function formatarNumero(){
			
			
			var num = document.getElementById('conv');
			var inp = num.value;
				
				
				if(event.keyCode == 8){
				var tamanho = inp.toString().lenght;
				//usar o char at para apagar
				alert(tamanho)
				}
				
				var x = inp.replace(',00', '');
				num.value = x + ',00';
		}