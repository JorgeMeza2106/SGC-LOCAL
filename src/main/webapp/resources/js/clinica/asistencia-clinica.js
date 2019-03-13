/**
 * @author desandlope0
 * Scripts para el control de asistencia de personal
 * en la clínica
 */


$(function (){
	
	//Busqueda de Personal
	$("#btnBuscarPersonal").on("click", function(){
		if(!$("#frmIngresoDni").valid()){
			return;
		}
		var dniPersona = $("#txtDniPersona").val();

		$.ajax ( { 
			type: "GET",
			url: "/SGC/admin_cl/asistencia?accion=buscarPersona",
			data: { "dniPersona" : dniPersona}, 
			statusCode: { 
				400: function(response){
					$("#frmIngresoDni").find("input[name='dniPersona']").parent().addClass("has-error")
						.append("<span class='help-block'>"+ response + "</span>");
				}
			},
			beforeSend: function(xhr){ 
				$("#btnBuscarPersonal").find("i").removeClass("fa-search").addClass("fa-refresh fa-spin fa-fw");
			},
			success : function(response){
				$("#divResultado").toggleClass("hidden", false);
				$("#divResultado #nombreCompletoPersonal").text(response);

			},
			complete: function(){
				$("#btnBuscarPersonal").find("i").removeClass("fa-refresh fa-spin fa-fw").addClass("fa-search");
			}
	   });
		
	});
	
	//Registro de Entrada
	$("#btnRegistrarEntrada").on("click", function(){
		var dniPersona = $("#txtDniPersona").val();
		console.log("jrebel");
		$.ajax ( { 
			type: "GET",
			
			url: "/SGC/admin_cl/asistencia/entrada", 
			data: { "dniPersona" : dniPersona}, 
			statusCode: { 
				500: function(response){
					var msg = response.responseJSON;
					notificarError(msg);
				}
			},
			success : function(fechaHora){
				notificarRegistroMovimiento({"movimiento" : "ENTRADA" , "hora": fechaHora })
			},
			beforeSend: function(xhr){ 
				$("#btnBuscarPersonal").find("i").removeClass("fa-search").addClass("fa-refresh fa-spin fa-fw");
			},
			complete: function(){
				$("#btnBuscarPersonal").find("i").removeClass("fa-refresh fa-spin fa-fw").addClass("fa-search");
			}
	   });
		
	});
	

	//Registro de Salida
	$("#btnRegistrarSalida").on("click", function(){
		var dniPersona = $("#txtDniPersona").val();
		console.log("wtf=" + dniPersona);
		console.log("jrebel");
		$.ajax ( { 
			type: "GET",
			url: "/SGC/admin_cl/asistencia/salida", 
			data: { "dniPersona" : dniPersona}, 
			statusCode: { 
				500: function(response){
					var msg = response.responseJSON;
					notificarError(msg);
				}
			},
			success : function(fechaHora){
				notificarRegistroMovimiento({"movimiento" : "SALIDA" , "hora": fechaHora })
			},
			beforeSend: function(xhr){ 
				$("#btnBuscarPersonal").find("i").removeClass("fa-search").addClass("fa-refresh fa-spin fa-fw");
			},
			complete: function(){
				$("#btnBuscarPersonal").find("i").removeClass("fa-refresh fa-spin fa-fw").addClass("fa-search");
			}
	   });
		
	});
	
	//Validación del Dni ingresado
	var validatorFrmIngresoDni = $("#frmIngresoDni").validate({
		rules: {
			txtdniPersona: {
				minlength: 8,
				maxlength: 8, 
				digits : true,
			}
		}, 
//		messages: {
//			txtdniPersona: {
//				digits: "",
//				
//			}
//		
//			//minlength: jQuery.validator.format("Please, at least {0} characters are necessary")
//		}
	});
	
	$("#frmIngresoDni").on('keyup keypress', function(e) {
		  var keyCode = e.keyCode || e.which;
		  if (keyCode === 13) { 
		    e.preventDefault();
		    return false;
		  }
	});
	
	$("#txtDniPersona").on("paste", function (evt) {
		if(!validatorFrmIngresoDni.element($("#txtdniPersona"))){
			$("#txtDniPersona").val("");
			//dniPersonaTemp = "";
		} 
	});
		
	function validarDni(evt){
		if(!validatorFrmIngresoDni.element($("#txtDniPersona"))){
			$("#txtDniPersona").val(dniPersonaTemp);
			dniPersonaTemp = "";
		} 
	}
	
	function notificarRegistroMovimiento(msg){
		swal({
			title : msg.movimiento + ' Registrada',
			text : "Hora de Registro: " + msg.hora,
			timer : 6000,
			type : "success"
		}).then(function() {
		}, function(dismiss) {
			if (dismiss === 'timer') {
				$("#txtDniPersona").val("");
			}
		});
	}
	
	function notificarError(msg){
		swal({
			title : msg.titulo,
			text : msg.descripcion,
			timer : 6000,
			type : "error"
		}).then(function() {
		}, function(dismiss) {
			if (dismiss === 'timer') {
				$("#txtDniPersona").val("");
			}
		});
	}
	
});