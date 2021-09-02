document.getElementById("erroBusca").style.visibility = "hidden"
function buscaPaciente() {
    var tbody = document.getElementById("tbody")
    document.getElementById("erroBusca").classList.add("hide")
    document.getElementById("txtFind").addEventListener("keyup", function () {

        var busca = document.getElementById("txtFind").value.toLowerCase()
        console.log(busca)


        for (var i = 0; i < tbody.childNodes.length; i++) {
            var achou = false
            var trow = tbody.childNodes[i]
            var td = trow.childNodes


            for (var j = 0; j < td.length; j++) {
                var nomePaciente = td[j].childNodes[0].nodeValue.toLowerCase()

                if (nomePaciente.indexOf(busca) >= 0) {
                    console.log(nomePaciente)
                    achou = true
                }

                if (achou) {
                    trow.style.display = "table-row"
                    console.log(nomePaciente.value)
                    document.getElementById("erroBusca").style.visibility = "hidden"
                } else {
                    trow.style.display = "none"
                    document.getElementById("erroBusca").style.visibility = "visible"
                }
            }


        }

    })

}
