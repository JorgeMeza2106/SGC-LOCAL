/**
 * 
 */
$(function() {
	
	$(".only-numbers").on(
			"keyup",
			function() {
				if (this.value != this.value.replace(/[^0-9]/g, '')) {
					this.value = this.value.replace(/[^0-9]/g, '');
				}
			});

	$(".only-numbers-dni").on(
			"keyup",
			function(evt) {
				console.log(this.value);
				console.log(this.value.length);
				if (this.value != this.value.replace(/[^0-9]/g, '')) {
					this.value = this.value.replace(/[^0-9]/g, '');
				}
				if (this.value.length > 8) {
					this.value = this.value.substring(0, 8);
				}
			});
	
//	$(".only-numbers-dni").on(
//			"keydown",
//			function(evt) {
////				if (this.value.length > 8) {
////					this.value = this.value.substring(0, 8);
////				}
//			});
	
});