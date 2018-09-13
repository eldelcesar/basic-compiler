class SymbolsTable {

    private var table: MutableList<Token> = mutableListOf()

    fun addSymbol(symbol: Token){
        table.add(symbol)
    }

    fun isEmpty(): Boolean{
        if (table.size > 0){
            return false
        }
        return true
    }

    override fun toString(): String {
        var str: String = ""
        for (symbol in table){
            str += symbol.toString()
        }
        return str
    }
}