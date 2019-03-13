window.onload = function() {
	//"use strict";	
	var boton = $("#boton");
	var socket = atmosphere;
	var request = {
		url : '/SGC/websockets/notificacion',
		contentType : "application/json",
		logLevel : 'debug',
		transport : 'websocket',
		trackMessageLength : true,
		fallbackTransport : 'long-polling',
		enableProtocol: false
	};
	
	request.onOpen = function(response) {
		console.log("en onOpen: "+ response.transport);		
	};
	
    request.onTransportFailure = function(errorMsg, request) {
    	console.log("en onTransportFailure");
        atmosphere.info(errorMsg);        
    };
	
	request.onMessage = function(response) {
		console.log("en onMessage");
		var message = response.responseBody;
		try {
			var json = atmosphere.util.parseJSON(message);
			console.log("tipo: " + json.tipo + ", mensaje: " + json.mensaje);
			var color = (json.tipo = 1) ? 'success' : 'danger';
						
			$('.top-right').notify({
			    message:  {text: json.mensaje},
	    		type: color
	     }).show();
		} catch (e) {
			console.log('This doesn\'t look like a valid JSON: ', message);
			return;
		}		
	};

//	request.onClose = function(response) {
//		console.log("en onClose: "+ response.transport);
//	}

	request.onError = function(response) {
		console.log("en onError: "+ response);		
	};

	var subSocket = socket.subscribe(request);
	console.log("subSocket:");
	console.log(subSocket);	

};


