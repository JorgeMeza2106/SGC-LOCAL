/**
 * Métodos personalizados para casos de validación típicos para una aplicación
 * 
 */
jQuery.validator.addMethod("exactlength", function(value, element, param) {
	return this.optional(element) || value.length == param;
}, $.validator.format("Please enter exactly {0} characters."));

$.validator.addMethod( "dni", function( value, element ) {
	return this.optional( element ) || /^\d{8}$/.test( value );
}, "El DNI especificado es inválido." );