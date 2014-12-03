<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Lector de Barras</title>
        <link rel="stylesheet" type="text/css" href="build/ext-theme-crisp/build/resources/ext-theme-crisp-all.css">
        <script type="text/javascript" src="build/ext-all.js"></script>
        <script>
            Ext.onReady(function () {
                var code = null;
                Ext.create('Ext.container.Viewport', {
                    layout: 'border',
                    items: [{
                            region: 'center',
                            title: 'Formulario',
                            items: [{
                                    id: 'textfield-code',
                                    xtype: 'textfield',
                                    name: 'code',
                                    fieldLabel: 'Código de Barras',
                                    listeners: {
                                        change: function (thisObj, newValue, oldValue, eOpts) {
                                            if (newValue !== '') {
                                                Ext.getCmp('btn-realizar').enable();
                                                code = newValue;
                                                thisObj.reset();
                                                var form = Ext.create('Ext.form.Panel');
                                                form.getForm().submit({
                                                    url: 'webresources/com.unl.banco.entities.historiales/get=' + newValue,
                                                    method: 'GET',
                                                    success: function (form, action) {
                                                        var data = action.result.data;
                                                        Ext.getCmp('text-html').setHtml(
                                                                '<b>Tipo de Cuenta: </b>' + data.tipoCuenta + '<br>'
                                                                +'<b>Número de Cuenta: </b>' + data.numeroCuenta + '<br>'
                                                                +'<b>Propietario de la Cuenta: </b>' + data.personaCuenta + '<br>'
                                                                +'<b>Tipo de Transacción: </b>' + data.transaccion + '<br>'
                                                                + '<b>Monto: </b>$' + data.cantidad);
                                                    },
                                                    failure: function (form, action) {
                                                        Ext.Msg.alert('Alerta', action.result.message);
                                                    }
                                                });
                                            }
                                        }
                                    }
                                }, {
                                    id: 'text-html',
                                    border: false,
                                    html: ''
                                }],
                            buttons: [{
                                    id: 'btn-realizar',
                                    text: 'Realizar Transacción',
                                    disabled: true,
                                    handler: function () {
                                        if (code !== null) {
                                            var form = Ext.create('Ext.form.Panel');
                                            form.getForm().submit({
                                                url: 'webresources/com.unl.banco.entities.historiales/set=' + code,
                                                method: 'GET',
                                                success: function (form, action) {
                                                    Ext.getCmp('textfield-code').focus(false, 200);
                                                    code = null;
                                                    Ext.getCmp('btn-realizar').disable();
                                                    Ext.getCmp('text-html').setHtml('');
                                                    Ext.Msg.alert('Mensaje', action.result.message);
                                                },
                                                failure: function (form, action) {
                                                    Ext.Msg.alert('Alerta', action.result.message);
                                                }
                                            });
                                        }
                                    }
                                }, {
                                    text: 'Cancelar',
                                    handler: function () {
                                        code = null;
                                        Ext.getCmp('btn-realizar').disable();
                                        Ext.getCmp('text-html').setHtml('');
                                        Ext.getCmp('textfield-code').focus(false, 200);
                                    }
                                }]
                        }]
                });

                Ext.getCmp('textfield-code').focus(false, 200);
            });
        </script>
    </head>
    <body>

    </body>
</html>
