/*<![CDATA[*/					
			var registrarContrato = function(){     				
				$('#modalGenerar').modal('hide');				
				 $.ajax({
			            url: "generarContrato",
			            type: "get",
			            data: {"nroHC": nroHC},
			            success: function(response){
			            	var color = "warning";
			            	if(response._1 != -1){	
			            		var table = $('#tablaCajaHC').DataTable();   							    
								var nuevo = $('#tablaCajaHC').dataTable()
							       .fnFindCellRowIndexes(nroHC, 0);
								table.cell(nuevo, 1).data(response._1);	
								console.log("ultimoC: " + ultimoC);
			            		if(ultimoC != -1){
			            			console.log("diferente -1")
			            			var ant = $('#tablaCajaHC').dataTable()
								       .fnFindCellRowIndexes(ultimoC, 1);
			            			cambiarOpcionUltimo(table.cell(ant, 6), table.cell(nuevo, 6), response._1);	
									table.cell(ant, 6).node().onclick = function(){
				            		    enviarDatos(table.cell(ant, 0).data(), "", "deshabilitar");
									}
			            		}else{
			            			console.log("igual  -1");
			            			cambiarUltimoGenerado(table.cell(nuevo, 6), response._1);	
			            		}			          
			            		table.cell(nuevo, 6).node().onclick = function(){
			            		    enviarDatos(table.cell(nuevo, 0).data(), "", "eliminar");
								}
			            		ultimoC = response._1;
								highlight(table.row(nuevo).node());
								color = "success";
			            	}
			            	$('.top-right').notify({
							    message:  {text: response._2},
					    		type: color	
					     }).show();
			            },
			            error: function(){
			                console.log("hubo error");
			            }
			            });			      		
    		};
    		
    		var eliminarContrato = function(){     				
				$('#modalEliminar').modal('hide');				
				 $.ajax({
			            url: "eliminarContrato",
			            type: "get",
			            data: {"nroHC": nroHC},
			            success: function(response){
			            	var color = "warning";
			            	if(response._1 == true){			     
			            		var table = $('#tablaCajaHC').DataTable();  
			            		var fila = $('#tablaCajaHC').dataTable()
							       .fnFindCellRowIndexes(nroHC, 0);
			            		celda = table.cell(fila, 6);
			            		table.cell(fila, 1).data("");	
			            		cambiarAGenerar(celda);			            		
								celda.node().onclick = function(){
			            		    enviarDatos(table.cell(fila, 0).data(), "", "generar");
								}
								highlight(table.row(fila).node());
								color = "success";
			            	}
			            	$('.top-right').notify({
							    message:  {text: response._2},
					    		type: color	
					     }).show();
			            },
			            error: function(){
			                console.log("hubo error");
			            }
			            });			      		
    		};
    		
    		var deshabilitarContrato = function(){   
    			console.log("deshabilitando");
				$('#modalDeshabilitar').modal('hide');				
				 $.ajax({
			            url: "deshabilitarContrato",
			            type: "get",
			            data: {"nroHC": nroHC},
			            success: function(response){
			            	var color = "warning";
			            	if(response._1 == true){			     
			            		var table = $('#tablaCajaHC').DataTable();  
			            		var fila = $('#tablaCajaHC').dataTable()
							       .fnFindCellRowIndexes(nroHC, 0);
			            		celda = table.cell(fila, 6);
			            		table.cell(fila, 1).data("");	
			            		cambiarAGenerar(celda);			            		
								celda.node().onclick = function(){
			            		    enviarDatos(table.cell(fila, 0).data(), "", "generar");
								}
								highlight(table.row(fila).node());
								color = "success";
			            	}
			            	$('.top-right').notify({
							    message:  {text: response._2},
					    		type: color	
					     }).show();
			            },
			            error: function(){
			                console.log("hubo error");
			            }
			            });			      		
    		};
    		
			function highlight(obj){
				console.log("obj: "+ obj);	
				obj.classList.add('warning'); 
				setTimeout(function(){
					obj.classList.remove('warning'); 
			   	}, 5000);
			}
			function cambiarOpcionUltimo(ant, nuevo, nroContrato){		
				console.log("3 parametros");
				var a_ant = $('<a />', {
   					'class': 'btn-link btn-lg',
   					'data-toggle': 'modal',
   					'href': '#modalDeshabilitar'
				});				
				a_ant.append("<span data-toggle='tooltip' data-placement='bottom' title='Deshabilitar' class='glyphicon glyphicon-off text-warning'></span>");
				console.log(a_ant.html());
				ant.data(a_ant.get(0).outerHTML+" <span class='glyphicon glyphicon-ok text-success pull-right'>").draw(false);				
				var a_nuevo = $('<a />', {
   					'class': 'btn-link btn-lg',
   					'data-toggle': 'modal',
   					'href': '#modalEliminar'
				});
				a_nuevo.append("<span data-toggle='tooltip' data-placement='bottom' title='Eliminar' class='glyphicon glyphicon-remove text-danger'></span>");
				nuevo.data(a_nuevo.get(0).outerHTML+" <span class='glyphicon glyphicon-ok text-success pull-right'>").draw(false);							
			}
			
			function cambiarUltimoGenerado(nuevo, nroContrato){
				console.log("2 parametros");
				var a_nuevo = $('<a />', {
   					'class': 'btn-link btn-lg',
   					'data-toggle': 'modal',
   					'href': '#modalEliminar'
				});
				a_nuevo.append("<span data-toggle='tooltip' data-placement='bottom' title='Eliminar' class='glyphicon glyphicon-remove text-danger'></span>");
				nuevo.data(a_nuevo.get(0).outerHTML+" <span class='glyphicon glyphicon-ok text-success pull-right'>").draw(false);							
			}
			
			function cambiarAGenerar(celda){
				var a_cell = $('<a />', {
   					'class': 'btn-link btn-lg',
   					'data-toggle': 'modal',
   					'data-target': "#modalGenerar"
				});				
				a_cell.append("<img data-toggle='tooltip' data-placement='bottom' title='Generar Contrato' src='/SGC/resources/icons/contrato.png' height='25' width='25'></img>");								
				celda.data(a_cell.get(0).outerHTML).draw(false);
			}
			///SGC/src/main/webapp/resources/icons/contrato.png
			/*]]>*/