
var panels = {
	logistica: '#content-logistica',
	monitoreo: '#content-monitoreo',
	reporte: '#content-reportes',
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
	});
	//bind menu item monitoreo
	$('.icon-bar .item.monitoreo').click(function() {
		$(panels.logistica).hide();
		$(panels.monitoreo).show();
		$(panels.reporte).hide();
	});
	//bind menu item reporte
	$('.icon-bar .item.reporte').click(function() {
		$(panels.logistica).hide();
		$(panels.monitoreo).hide();
		$(panels.reporte).show();
	});
	//bind menu item enviar ranking
	$('.icon-bar .item.ranking').click(function() {
		if(BusinessDelegate) {
			BusinessDelegate.EnviarRanking();
		}
	});
	
	dataPane.start();
	logistica.start();
	reportes.start();
});



/*
 * Controlador Business Delegate 
 */
var BusinessDelegate = {
		AsignarOrdenAVenta: function(venta, despacho) {
			if(!venta || !despacho) {
				return;
			}
			$.post('Web/REST/asociarDespachoAVenta/' + venta.nro + '/' + despacho.numero,
			function(response) {
				alert("Pasó");
			},
			'json').fail(function() {
				alert("Falló");
			});
		},
		
		EnviarRanking: function() {
			$.post('Web/REST/enviarRanking', function(response) {
				alert('Ranking enviado correctamente');
			}, 'text').fail(function() {
				alert('Ranking falló');
			});
		}
		
};



/***
	Objetos
***/
function Venta(nro, estado, latitud, longitud, fecha, monto, modulo) {
	this.nro = nro;
	this.estado = estado;
	this.latitud = latitud;
	this.longitud = longitud;
	this.fecha = fecha;
	this.monto = monto;
	this.modulo = modulo;
	this.items = [];
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

function OrdenDespacho(estado, latitud, longitud, fecha, monto) {
	this.estado = estado;
	this.latitud = latitud;
	this.longitud = longitud;
	this.fecha = fecha;
	this.monto = monto;
	this.items = [];
}

function ItemOrdenDespacho(articulo, cantidad) {
	this.articulo = articulo;
	this.cantidad = cantidad;
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
		v.find('#confirmar-despacho').removeClass('disabled');
		v.find('.nro-venta').html(sale.nro);
		v.find('.latitud').html(sale.latitud);
		v.find('.longitud').html(sale.longitud);
		
		var selectedName = sale.Triangular(despachos);
		
		d.find('.despacho[data-id="' + selectedName + '"]').addClass('recomended');
		
		if(sale.asociada && sale.asociada.numero) {
			d.find('.despacho[data-id=' + sale.orden.numero + ']').addClass('seleccionado');
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
				BusinessDelegate.AsignarOrdenAVenta(venta, despacho);
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
		d.find('.portal').html(sale.portal);
		d.find('.latitud').html(sale.latitud);
		d.find('.longitud').html(sale.longitud);
		
		if(sale.orden && sale.orden.nombre) {
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

//Controlador del panel de monitoreo
var monitoreo = {

};

//Controlador del panel de reportes
var reportes = {
	start: function() {
		this.bindDataPane();
	},
	bindDataPane: function() {
		$(panels.reporte + ' .portal-venta').click(function(sender) {
			if(sender) {
				var nro = $(sender).attr('data-id');
				//Buscar la venta en la coleccion
				//dataPane.loadSaleDetail();
				
				//Borrar
				$(panels.dataDispatch).hide();
				$(panels.dataSaleView).show();
				dataPane.toggle();
				//Borrar
			}
		});
	}
};