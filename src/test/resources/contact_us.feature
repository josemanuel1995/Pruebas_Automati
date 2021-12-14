Feature: Formulario de contacto
  Yo como PO
  Quiero un formulario de contacto
  Para que mis clientes/ proveedores tengan un canal de comunicacion con la empresa

  Background: el usuario se encuentra en el formulario de contacto
    Given que el usuario esta en el formulario de contacto

  Scenario Outline:  Deberia enviar el mensaje de contacto cuando enviamos todos los campos requeridos
    When el usuario completa el formulario con los campos requeridos
      |<cabecera>       | <email>      | <ordenReferencia> | <mensaje>                                       |
      |Customer Service | pepito@g.com | 01235622          | Por favor enviar la orden lo mas rapido posible |
    Then el usuario deberia poder ver el mensaje de confirmacion que se envio el mensaje
    Examples:
      |cabecera         | email            | ordenReferencia   | mensaje                                                      |
      |Customer Service | pepito@g.com     | 01235622          | Por favor enviar la orden lo mas rapido posible              |
      |Webmaster        | pepito@perez.com | 54448410          | Por favor habilitar la opcion de pago con tarjeta de credito |

    Scenario Outline: El campo orden referencia solo debe admitir valores numericos
      When el usuario ingresa el valor <"03121544"> en campo orden referencia
      Then el usuario deberia poder ver el color del campo es <"verde">
      Examples:
        | "03121544" | "verde" |
        | "@#$%."    | "rojo"  |
        | "prueba"   | "rojo"  |

    Scenario: El campo email solo admite emails validos
      Given que estoy en la pagina de inicio
      And selecciono la opcion Contact us
      When Selecciono la cabecera Customer service
      And ingreso el email "el@pe.com"
      And ingreso orden de referencia "00015522"
      And ingreso el mensaje "Por favor validar las referencias duplicadas en la pagina de ventas"
      Then el mensaje fue enviado