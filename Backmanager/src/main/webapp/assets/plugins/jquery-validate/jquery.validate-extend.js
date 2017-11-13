
/**
 * jquery validator 验证样式扩展
 * @author cuics
 */
jQuery.extend(jQuery.validator.defaults, {
	 doNotHideMessage: true, //this option enables to show the error/success messages on tab switch.
	 errorElement: 'span', //default input error message container
     errorClass: 'help-block', // default input error message class
     focusInvalid: false, // do not focus the last invalid input
     invalidHandler: function (event, validator) { //display error alert on form submit              
     },
     highlight: function (element) { // hightlight error inputs
         $(element)
             .closest('.help-block').removeClass('fa-check'); // display OK icon
         $(element)
             .closest('.form-group').removeClass('has-success has-feedback').addClass('has-error has-feedback'); // set error class to the control group
     },

     unhighlight: function (element) { // revert the change dony by hightlight
         $(element)
             .closest('.form-group').removeClass('has-error has-feedback'); // set error class to the control group
     },

     success: function (label) {//.addClass('help-block fa-check')
         label
             .addClass('valid') // mark the current input as valid and display OK icon
         .closest('.form-group').removeClass('has-error has-feedback').addClass('has-success has-feedback'); // set success class to the control group
     }
});