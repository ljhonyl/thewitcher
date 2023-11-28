package es.tiernoparla.thewitcher.modelo.PersonajesMock

import es.tiernoparla.thewitcher.modelo.Personaje

class PersonajesMock {
    private val listaPersonajes: MutableList<Personaje> = mutableListOf<Personaje>()

    constructor(){
        cargarLista()
    }
    fun cargarLista(){
        val p1: Personaje= Personaje(1,"Geralt de Rivia","Lobo Blanco", "Humano","Geralt de Rivia es un brujo, cazador de monstruos mutado con cabello blanco y habilidades sobrenaturales. Destaca por su destreza en la lucha, su armadura distintiva y su naturaleza pragmática. Geralt se enfrenta a dilemas morales, desarrolla relaciones con otros personajes y navega por un mundo lleno de magia, conflictos y criaturas sobrenaturales. Su historia se caracteriza por una combinación de acción, drama y decisiones impactantes.","")
        val p2: Personaje= Personaje(2,"Cirilla Fiona Elen Riannon","Golondrina","Humana",
            "Ciri es una mujer joven con cabello blanco. Dotada de habilidades especiales, descendiende de la vieja sangre, posee la capacidad de viajar entre dimensiones y es una hábil espadachina. Su historia, entrelazada con la de Geralt, se centra en su búsqueda y los desafíos que enfrenta, revelando conexiones importantes con eventos que afectan al mundo. Valiente e independiente, Ciri desempeña un papel crucial en la narrativa de The Witcher.","")
        val p3:Personaje= Personaje(3,"Triss Merigold","Decimocuarta del monte","Humana",
            "Triss Merigold es una hechicera . Su papel se destaca por su participación en eventos políticos y su valiosa contribución en la lucha contra criaturas sobrenaturales. Su personalidad se caracteriza por su inteligencia y determinación.","")
        val p4:Personaje= Personaje(4,"Yennefer","Yennefer de Vengerberd","Humana","Yennefer de Vengerberg es una poderosa hechicera. Su historia está marcada por la adversidad y la superación. Nacida con una deformidad facial, Yennefer sufre una infancia difícil antes de descubrir sus habilidades mágicas. A lo largo de los años, perfecciona su arte en la hechicería y se convierte en una figura influyente en el mundo de la magia. Yennefer es conocida por su astucia y fuerte personalidad. Su relación con Geralt de Rivia es un hilo constante en su historia, atravesando desafíos y altibajos. Aunque su búsqueda de la juventud eterna la lleva a tomar decisiones difíciles, Yennefer demuestra ser una hechicera formidable.","")
        listaPersonajes.add(p1)
        listaPersonajes.add(p2)
        listaPersonajes.add(p3)
    }

    fun getListaPersonajes():List<Personaje>{
        return listaPersonajes
    }
}