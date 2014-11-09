
var panels = {
	logistica: '#content-logistica',
	monitoreo: '#content-monitoreo',
	reporte: '#content-reportes',
	audit: '#content-audit',
	ranking: '#content-ranking',
	data: '#data-pane',
	dataDispatch: '#data-seleccionar-despacho',
	dataSaleView: '#data-ver-detalle-venta'
};

$(document).ready(function() {
	//bind menu item logistica
	$('.icon-bar .item.logistica').click(function() {
		$(panels.logistica).show();
		$(panels.monitoreo).hide();
		$(panels.reporte).hide();
		$(panels.audit).hide();
		$(panels.ranking).hide();
	});
	//bind menu item monitoreo
	$('.icon-bar .item.monitoreo').click(function() {
		$(panels.logistica).hide();
		$(panels.monitoreo).show();
		$(panels.reporte).hide();
		$(panels.audit).hide();
		$(panels.ranking).hide();
	});
	//bind menu item reporte
	$('.icon-bar .item.reporte').click(function() {
		$(panels.logistica).hide();
		$(panels.monitoreo).hide();
		$(panels.reporte).show();
		$(panels.audit).hide();
		$(panels.ranking).hide();
	});
	//bind menu item auditoria
	$('.icon-bar .item.auditoria').click(function() {
		$(panels.logistica).hide();
		$(panels.monitoreo).hide();
		$(panels.reporte).hide();
		$(panels.audit).show();
		$(panels.ranking).hide();
	});
	//bind menu item enviar ranking
	$('.icon-bar .item.ranking').click(function() {
		$(panels.logistica).hide();
		$(panels.monitoreo).hide();
		$(panels.reporte).hide();
		$(panels.audit).hide();
		$(panels.ranking).show();
	});
	
	dataPane.start();
	logistica.start();
	reportes.start();
	auditoria.start();
	ranking.start();
});



/*
 * Controlador Business Delegate 
 */
var BusinessDelegate = {
		AsignarOrdenAVenta: function(venta, despacho) {
			if(!venta || !despacho) {
				return;
			}
			return $.post('Web/REST/asociarDespachoAVenta/' + venta.nro + '/' + despacho.numero);
		},
		
		EnviarRanking: function() {
			return $.post('Web/REST/enviarRanking');
		},
		
		Logs: function() {
			return $.get('Web/REST/logs');
		}
		
};



/***
	Objetos
***/
function Venta(nro, latitud, longitud, fecha, monto, modulo) {
	this.nro = nro;
	this.latitud = latitud;
	this.longitud = longitud;
	this.fecha = fecha;
	this.monto = monto;
	this.modulo = modulo;
	this.ventaItems = [];
	this.orden = {};
}

Venta.prototype.Triangular = function(depachos) {
	var min = undefined;
	var selected = undefined;
	if(despachos && this.latitud && this.longitud) {
		for(var i in despachos) {
			var d = despachos[i];
			if(d && d.latitud != undefined && d.longitud != undefined) {
				var mod = Math.sqrt( Math.pow((this.latitud-d.latitud),2) + Math.pow((this.longitud-d.longitud),2) );
				if(!min || mod < min) {
					min = mod;
					selected = d.numero;
				}
			}
		}
	}
	return selected;
};

function Item(articulo, cantidad) {
	this.articulo = articulo;
	this.cantidad = cantidad;
}

function OrdenDespacho(nroDespacho, nroVenta, estado, portal, fecha) {
	this.nroDespacho = nroDespacho;
	this.nroVenta = nroVenta;
	this.estado = estado;
	this.portal = portal;
	this.fecha = fecha;
}

function Despacho(nombre, latitud, longitud, numero) {
	this.numero = numero;
	this.nombre = nombre;
	this.latitud = latitud;
	this.longitud = longitud;
}






//Controlador del panel de datos
var dataPane = {
	_showing: false,
	_open: function() {
		$(panels.data).css('right', '0px');
		this._showing = true;
	},
	_close: function() {
		$(panels.data).css('right', '-60%');
		this._showing = false;
	},
	start: function() {
		$(panels.data + ' .data-pane-close').click(function() {
			dataPane._close();
		});
	},
	toggle: function() {
		if(!this._showing) {
			this._open();
		} else {
			this._close();
		}
		return this;
	},
	loadDispatchSelection: function(sale) {
		//TODO
		this._close();
		$(panels.dataSaleView).hide();
		$(panels.dataDispatch).show();
		var d = $(panels.dataDispatch);
		var v = d.find('.data-header');
		
		d.find('.despacho').removeClass('seleccionado').removeClass('recomended');
		d.find('#confirmar-despacho').removeClass('disabled');
		v.find('.nro-venta').html(sale.nro);
		v.find('.latitud').html(sale.latitud);
		v.find('.longitud').html(sale.longitud);
		
		var selectedName = sale.Triangular(despachos);
		
		d.find('.despacho[data-id="' + selectedName + '"]').addClass('recomended');
		
		if(sale.asociada && sale.asociada.nroDespacho) {
			d.find('.despacho').unbind('click');
			d.find('.despacho[data-id="' + sale.orden.numero + '"]').addClass('seleccionado');
			d.find('#confirmar-despacho').addClass('disabled').unbind('click');
		} else {
			d.find('.despacho').unbind('click').click(function() {
				d.find('.despacho').removeClass('seleccionado');
				$(this).addClass('seleccionado');
			});
			d.find('#confirmar-despacho').click(function() {
				var numeroSeleccionado = d.find('.despacho.seleccionado').attr('data-id');
				var venta = ventas[v.find('.nro-venta').html()];
				var despacho = despachos[numeroSeleccionado];
				//Mandar al server
				$(panels.dataDispatch).find('.loader').show();
				BusinessDelegate.AsignarOrdenAVenta(venta, despacho)
				.done(function(orden) {
					if(venta && orden) {
						/*
						 * estado: null
						 * fecha: 1415470461000
						 * idModulo: 2
						 * items: null
						 * nroDespacho: 4
						 * nroVenta: 3
						 */
						venta.asociada = new OrdenDespacho(orden.nroDespacho, orden.nroVenta, orden.estado, orden.idModulo, orden.fecha);
						$(panels.dataDispatch).find('.loader').hide();
						$(panels.logistica).find('.venta[data-id="' + venta.nro + '"]')
							.removeClass('pending')
							.addClass('done')
							.find('.assign.pending').hide();
							$(panels.logistica).find('.venta[data-id="' + venta.nro + '"] .assign.done').show();
						d.find('#confirmar-despacho').addClass('disabled');
						
						$(panels.monitoreo).find('.venta-resumen[data-id="' + venta.nro + '"] .monitoreo-con-despacho').html('Si');
						$(panels.monitoreo).find('.venta-resumen[data-id="' + venta.nro + '"] .monitoreo-status').html(venta.asociada.estado);
						
						dataPane._close();
					}
				}).fail(function() {
					//Por ahora nada
					$(panels.dataDispatch).find('.loader').hide();
					alert('Falló el envio de orden de despacho');
				});
			});
		}
		
		this._open();
		return this;
	},
	loadSaleDetail: function(sale) {
		//TODO
		this._close();
		$(panels.dataDispatch).hide();
		$(panels.dataSaleView).show();
		var d = $(panels.dataSaleView);
		
		d.find('.nro-venta').html(sale.nro);
		d.find('.fecha').html(sale.fecha);
		d.find('.monto').html(sale.monto);
		d.find('.portal').html(sale.modulo);
		d.find('.latitud').html(sale.latitud);
		d.find('.longitud').html(sale.longitud);
		
		if(sale.orden && sale.orden.numero) {
			//Mostrar el despacho asignado y el estado de la orden
		}
		
		this._open();
		return this;
	}
};

//Controlador del panel de logistica
var logistica = {
	start: function() {
		this.bindDataPane();
	},
	bindDataPane: function() {
		$(panels.logistica + ' .venta').click(function(sender) {
			var nro = $(this).attr('data-id');
			var venta = null;
			//Buscar la venta en la coleccion
			if(ventas) {
				venta = ventas[nro];
			}
			if(venta) {
				dataPane.loadDispatchSelection(venta);	
			}
			
//			//Borrar
//			$(panels.dataSaleView).hide();
//			$(panels.dataDispatch).show();
//			dataPane.toggle();
//			//Borrar
		});
	}
};

//Controlador del panel de reportes
var reportes = {
	start: function() {
		this.bindDataPane();
	},
	bindDataPane: function() {
		$(panels.reporte + ' .portal-venta').click(function() {
			var nro = $(this).attr('data-id');
			//Buscar la venta en la coleccion
			console.log('here');
			var sale = ventas[nro];
			dataPane.loadSaleDetail(sale);
			
			//Borrar
//			$(panels.dataDispatch).hide();
//			$(panels.dataSaleView).show();
//			dataPane.toggle();
			//Borrar
		});
	}
};

//Controlador del panel de enviar ranking
var ranking = {
		start: function() {
			$(panels.ranking).find('.enviar-ranking').click(function() {
				if(BusinessDelegate) {
					$(panels.ranking).find('.loader').show();
					BusinessDelegate.EnviarRanking()
						.done(function() {
							$(panels.ranking).find('.loader').hide();
						})
						.fail(function() {
							$(panels.ranking).find('.loader').hide();
						});
				}
			});
		}
};

//Controlador del panel de auditoria
var auditoria = {
		_lastLogs: [],
		_logs: {},
		_blocking: false,
		_last_request: 0,
		_request_id: 0,
		_ID: function() {
			return ++this._request_id;
		},
		start: function() {
			setInterval(function() {
				if(!this._blocking) {
					$(panels.audit).find('.loader').show();
					var id = auditoria._ID();
					auditoria._last_request = id;
					auditoria._blocking = true;
					BusinessDelegate.Logs()
						.done(function(logs) {
							if(logs != null && logs != undefined) {
								for(var i=0; i<logs.length; i++) {
									var key = logs[i].idModulo + '::' + logs[i].fecha;
									auditoria._logs[key] = logs[i];
								}
								auditoria.setLogs().done(function() {
									auditoria._blocking = false;
									$(panels.audit).find('.loader').hide();
								});
							}
						})
						.fail(function() {
							$(panels.audit).find('.loader').hide();
							console.log('No se pudo traer los logs');
							auditoria._blocking = false;
						});
				}
			}, 2700);
		},
		setLogs: function() {
			var dfd = $.Deferred();
			if(this._logs) {
				console.log('Setting..');
				$('.log-items').empty();
				for(var i=0 in this._logs) {
					var currentLog = this._logs[i];
					if(currentLog) {
						var template = $('#log-template .log').clone();
						template.find('.log-portal').html(currentLog.idModulo);
						template.find('.log-date').html(currentLog.fecha);
						template.find('.log-message').html(currentLog.log);
						$('.log-items').append(template);
					}
				}
				dfd.resolve();
			}
			return dfd;
		}
};