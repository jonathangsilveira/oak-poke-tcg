package br.edu.jgsilveira.portfolio.oakpokedex.tcg.data


data class Cards(val cards: List<Card> = listOf()) {
    data class Card(
        val id: String = "",
        val name: String = "",
        val nationalPokedexNumber: Int = 0,
        val imageUrl: String = "",
        val imageUrlHiRes: String = "",
        val types: List<String> = listOf(),
        val supertype: String = "",
        val subtype: String = "",
        val hp: String = "",
        val retreatCost: List<String> = listOf(),
        val convertedRetreatCost: Int = 0,
        val number: String = "",
        val artist: String = "",
        val rarity: String = "",
        val series: String = "",
        val `set`: String = "",
        val setCode: String = "",
        val attacks: List<Attack> = listOf(),
        val resistances: List<Resistance> = listOf(),
        val weaknesses: List<Weaknesse> = listOf(),
        val evolvesFrom: String = "",
        val ability: Ability = Ability(),
        val text: List<String> = listOf()
    ) {
        data class Resistance(
            val type: String = "",
            val value: String = ""
        )

        data class Attack(
            val cost: List<String> = listOf(),
            val name: String = "",
            val text: String = "",
            val damage: String = "",
            val convertedEnergyCost: Int = 0
        )

        data class Ability(
            val name: String = "",
            val text: String = "",
            val type: String = ""
        )

        data class Weaknesse(
            val type: String = "",
            val value: String = ""
        )
    }
}