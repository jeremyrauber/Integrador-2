$(function() {
	
	 $('#login-form-link').click(function(e) {
		 $("#login-form").delay(100).fadeIn(100);
	     $("#register-form").fadeOut(100);
	     $('#register-form-link').removeClass('active');
	     $(this).addClass('active');
	     e.preventDefault();
	});
   	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	
	
});

function validateForm() {
	
	var aux = 0;
    var x = document.forms["register-form"]["nome"].value;
    if (x == "") {
        document.getElementById("val_nome").innerHTML = "Nome deve ser preenchido";
        document.getElementById("val_nome").parentElement.setAttribute("class", "form-group has-error");
        aux++;
    }else{
    	document.getElementById("val_nome").parentElement.setAttribute("class", "form-group");
    	document.getElementById("val_nome").innerHTML = "Nome";
    }
    
    var x = document.forms["register-form"]["data_nasc"].value;
    if (x == "") {
        document.getElementById("val_data_nasc").innerHTML = "Data de Nascimento deve ser preenchida";
        document.getElementById("val_data_nasc").parentElement.setAttribute("class", "form-group has-error");
        aux++;
    }else{
    	document.getElementById("val_data_nasc").parentElement.setAttribute("class", "form-group");
    }
    
    var x = document.forms["register-form"]["login"].value;
    if (x == "") {
        document.getElementById("val_login").innerHTML = "Login deve ser preenchido";
        document.getElementById("val_login").parentElement.setAttribute("class", "form-group has-error");
        aux++;
    }else{
    	document.getElementById("val_login").parentElement.setAttribute("class", "form-group");
    }
    
    var x = document.forms["register-form"]["endereco"].value;
    if (x == "") {
        document.getElementById("val_endereco").innerHTML = "Endereco deve ser preenchido";
        document.getElementById("val_endereco").parentElement.setAttribute("class", "form-group has-error");
        aux++; 
    }else{
    	document.getElementById("val_endereco").parentElement.setAttribute("class", "form-group");
    }
    
    var z = document.forms["register-form"]["text_senha"].value;
    var y = document.forms["register-form"]["repetir_senha"].value; 
    if (z == "" || y=="") { 	
        document.getElementById("val_senha").innerHTML = "Senha devem ser preenchida";
        document.getElementById("val_senha").parentElement.setAttribute("class", "form-group has-error");
        aux++;
    }else{
    	document.getElementById("val_senha").parentElement.setAttribute("class", "form-group");
    }
    
    if(z!=y){
    	document.getElementById("val_senha").innerHTML = "Senhas nao sao iguais";
        document.getElementById("val_senha").parentElement.setAttribute("class", "form-group has-error");
        aux++;
    }else if(z==y && z!='' && y!=''){
    	document.getElementById("val_senha").parentElement.setAttribute("class", "form-group has-success");
    	document.getElementById("val_senha").innerHTML = "Senhas ok";
    }
    
    var x = document.forms["register-form"]["email"].value;
    if (x == "") {
        document.getElementById("val_email").innerHTML = "Email deve ser preenchido";
        document.getElementById("val_email").parentElement.setAttribute("class", "form-group has-error");
        aux++; 
    }else{
    	document.getElementById("val_email").parentElement.setAttribute("class", "form-group");
    }
    
    var x = document.forms["register-form"]["cep"].value;
    if (x == "") {
        document.getElementById("val_cep").innerHTML = "CEP deve ser preenchido";
        document.getElementById("val_cep").parentElement.setAttribute("class", "form-group has-error");
        aux++; 
    }else{
    	document.getElementById("val_cep").parentElement.setAttribute("class", "form-group");
    }
    
    var x = document.forms["register-form"]["bairro"].value;
    if (x == "") {
        document.getElementById("val_bairro").innerHTML = "Bairro deve ser preenchido";
        document.getElementById("val_bairro").parentElement.setAttribute("class", "form-group has-error");
        aux++; 
    }else{
    	document.getElementById("val_bairro").parentElement.setAttribute("class", "form-group");
    }
    
    var x = document.forms["register-form"]["cidade"].value;
    if (x == "") {
        document.getElementById("val_cidade").innerHTML = "Cidade deve ser preenchida";
        document.getElementById("val_cidade").parentElement.setAttribute("class", "form-group has-error");
        aux++;    
    }else{
    	document.getElementById("val_cidade").parentElement.setAttribute("class", "form-group");
    }
    
    var x = document.forms["register-form"]["estado"].value;
    if (x == "") {
        document.getElementById("val_estado").innerHTML = "Estado deve ser preenchido";
        document.getElementById("val_estado").parentElement.setAttribute("class", "form-group has-error");
        aux++; 
    }else{
    	document.getElementById("val_estado").parentElement.setAttribute("class", "form-group");
    }
    
  
    if (aux > 0){
    	  return false;
    }
    
}