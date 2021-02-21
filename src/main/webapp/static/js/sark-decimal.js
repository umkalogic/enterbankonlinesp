/**
 * #author  	: SARK PJ
 * #author url  : http://iamsark.com
 * #purpose 	: decimal masking and behaviour
 * #version		: 1.0.0
 */
$( document ).ready(function(){
	
	//Maximum length of decimal value
	var fieldLength = 15; 
	
	/**
	 * 1. To set field selection
	 * 2. Decimal replacer integer
	 * 3. Return selected integer in field
	 */
	
	var sark_decimal_utils = function(){
		this.selectpoint = function( _target, _start, _end ){
			_end =  typeof _end == "undefined" ? _start: _end;
			_target[0].selectionStart = _start;
			_target[0].selectionEnd	  = _end;
		}	
		this.replaceStr = function( start, end, character, old ) {
		    return old.substr( 0, start ) + character + old.substr( end+character.length );
		};	
		this.getSelectVal = function( _target ){
			var val = "";
			for( var i = _target[0].selectionStart; i < _target[0].selectionEnd; i++ ){
				val += _target.val()[i];
			}
			return val;
		};
	};	
	
	// To create new object function variable
	var sark_behav = new sark_decimal_utils;	
	
	$( document ).on( "focusin blur", "input[data-behaviour=decimal]", function(e){
		var curElem = $( this );
		if( e.type == "focusin" && curElem.val().length == 0 ){
			curElem.val( "0.00" );
			sark_behav.selectpoint( curElem, 0, curElem.val().length );
		} else if( e.type == "focusout" && curElem.val() == "0.00" || curElem.val() == "0.0" ){
			$( this ).val( "" );
		}
		if( $( this ).val() != "" && e.type == "focusout" ){
			var fraction = curElem.val().split( "." );
			if( fraction[1].length == 1 ){
				curElem.val( curElem.val() + "0" );
			} else if( fraction[1].length == 0 ){
				curElem.val( curElem.val() + "00" );
			}
		}
	});
	
	//Field keydown for all key  
	$( document ).on(  "keydown", "input[data-behaviour=decimal]", function(e){
		var curElem  = $( this );
		var keycode  = e.keyCode;
		var inpVal 	 = String.fromCharCode( ( 96 <= keycode && keycode <= 105 ) ? keycode-48 : keycode );		
		var fraction = curElem.val().split( "." );
		var strSlct	 = curElem[0].selectionStart;
		var endSlct  = curElem[0].selectionEnd;
		var next     = true;
		var start    = 0;
		var mxLength = typeof curElem.attr( "maxlength" ) == "undefined" ? fieldLength : curElem.attr( "maxlength" );		
		if( strSlct == endSlct ){
			if( !( ( keycode < 48 || keycode > 57 )  && ( keycode < 96 || keycode > 105 ) ) ){
				if( ( fraction[0].length <= strSlct || mxLength <= curElem.val().length ) && fraction[0].length != strSlct && fraction[1].length <= 2 && strSlct != curElem.val().length  ){
					curElem.val( sark_behav.replaceStr( strSlct, strSlct, inpVal, curElem.val() ) );
				} 
				if( fraction[0].length <= strSlct && fraction[1].length < 2 ){
					curElem.val( fraction[0]+ "."+ fraction[1] + "" + inpVal );
				}
				if( fraction[0].length == strSlct && curElem.val().length < mxLength  ){
					curElem.val( fraction[0]+ "" +inpVal+ "."+ fraction[1] );
				} else if( fraction[0].length >= strSlct && curElem.val().length < mxLength  ){
					var val = curElem.val();
					curElem.val( [val.slice(0, strSlct), inpVal, val.slice(strSlct)].join('') );
				}
				if( next ){
					start = start == 0 ? strSlct + 1 : start;
					sark_behav.selectpoint( curElem, start, start );
				}
				return false;
			} else if( ( keycode == 8 && fraction[0].length+1 == strSlct ) || ( keycode == 46 && fraction[0].length == strSlct ) ){
				e.preventDefault();
			} else if( keycode == 190 || keycode == 110 ){
				if( fraction[0].length >= strSlct ){
					sark_behav.selectpoint( curElem, fraction[0].length + 1, fraction[0].length + 1 );
				}
			}	
		} else if( strSlct != endSlct && sark_behav.getSelectVal( curElem ).indexOf( "." ) != -1 ){			
			if( keycode == 46 || keycode == 8  || !( ( keycode < 48 || keycode > 57 )  && ( keycode < 96 || keycode > 105 ) )  ){				
				var keyval = keycode == 46 || keycode == 8 ? "." : inpVal+".";
				curElem.val( sark_behav.replaceStr( strSlct, endSlct-keyval.length, keyval, curElem.val() ) );
				var splval = curElem.val().split( "." )[1].length == 0 ? "00" : "0";
				curElem.val( curElem.val()+splval );
				sark_behav.selectpoint( curElem, strSlct+1, strSlct+1 );
				e.preventDefault();
			}
			
		}		
		if( keycode != 9 && keycode != 13 && keycode != 8 && keycode != 46 && ( keycode < 37 || keycode > 40 ) ){
			e.preventDefault();
		}
	});	
});
