

let cajaInicio=document.querySelector(".inicio");
let cajaRegistro=document.querySelector(".registrarse")

let contrasenia1=document.getElementById("contrasenia");
let contrasenia2=document.getElementById("contrasenia2");

let botonRegistro=document.getElementById("BotonR");
let t1=contrasenia1.value;
let t2=contrasenia2.value;


function mostrarRegistro() {
    cajaInicio.classList.remove("visible");
    cajaInicio.classList.add("oculto");

    cajaRegistro.classList.remove("oculto");
    cajaRegistro.classList.add("visible");
}

function mostrarInicio() {
    cajaRegistro.classList.remove("visible");
    cajaRegistro.classList.add("oculto");

    cajaInicio.classList.remove("oculto");
    cajaInicio.classList.add("visible");
}


function comprobarContrasenia(){
      let t1 = contrasenia1.value;
    let t2 = contrasenia2.value;


 
if(t1===t2 && t1.trim()!==""){
botonRegistro.style.pointerEvents="auto";
botonRegistro.style.cursor="pointer";
botonRegistro.style.opacity=1;

}
else{
botonRegistro.style.pointerEvents="none";
botonRegistro.style.cursor="not-allowed";
botonRegistro.style.opacity=0.6;
    

}
}

