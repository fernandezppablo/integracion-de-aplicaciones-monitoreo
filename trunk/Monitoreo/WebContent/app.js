
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
	
	dataPane.start();
	logistica.start();
	reportes.start();
});





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
	if(despachos && this.latitud && this.longitud) {
		for(var i in despachos) {
			var d = despachos[i];
			if(d && d.latitud != undefined && d.longitud != undefined) {
				var mod = sqrt( pow((this.latitud-d.latitud),2) + pow((this.longitud-d.longitud),2) );
				if(mod < min) {
					min = mod;
				}
			}
		}
	}
	return min;
}

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

function Despacho(nombre, latitud, longitud) {
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
		
		d.find('.despacho').removeClass('seleccionado');
		d.find('#confirmar-despacho').removeClass('disabled');
		d.find('.nro-venta').html(sale.nro);
		d.find('.latitud').html(sale.latitud);
		d.find('.longitud').html(sale.longitud);
		
		if(sale.orden && sale.orden.nombre) {
			d.find('.despacho[data-id=' + sale.orden.nombre + ']').addClass('seleccionado');
			d.find('#confirmar-despacho').addClass('disabled').unbind('click');
		} else {
			d.find('#confirmar-despacho').click(function() {
				var nombreSeleccionado = d.find('.despacho.seleccionado').attr('data-id');
				//Mandar al server
			});
		}
		
		this.open();
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
		
		this.open();
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
			if(sender) {
				var nro = $(sender).attr('data-id');
				//Buscar la venta en la coleccion
				//dataPane.loadDispatchSelection();
				
				//Borrar
				$(panels.dataSaleView).hide();
				$(panels.dataDispatch).show();
				dataPane.toggle();
				//Borrar
			}
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