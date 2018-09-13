import enums.*
import java.io.File

class LexAnalyser {

    private var tokens = SymbolsTable()
    private var errors = SymbolsTable()
    private var symbolTable = SymbolsTable()
    private var buffer = ""
    private var lineCounter = 0
    private var charCounter = 0
    private var test = ""

    // Executes the Lex Analysis and returns either the tokens table or the symbols table
    fun runLexAnalysis(file: String): MutableList<SymbolsTable> {
        val arr: MutableList<SymbolsTable> = mutableListOf<SymbolsTable>()
        readFile(file)

        if (this.errors.isEmpty()){
            arr.add(this.tokens)
            arr.add(this.symbolTable)
        }
        else {
            arr.add(this.errors)
        }
        return arr
    }

    // Reads the File
    private fun readFile(fileName: String) {
        File(fileName).forEachLine {
            lineCounter++
            it.forEachIndexed { index, element -> runBuffer(index, element)}
        }
    }

    private fun runBuffer(i: Int, c: Char) {
        val input = c.toString()
        this.charCounter = i
        test = input
        when {
            isPunctuationSign(input) -> {
                if (buffer != "") {
                    runAnalysis()
                    var token = Token(KeyTypeEnum.SIGNO_PUN.name, input, lineCounter, charCounter+1, TokenTypeEnum.SUCCESS)
                    tokens.addSymbol(token)
                    buffer = ""
                }
            }
            isBlank(input) -> {
               if (buffer != "") {
                   runAnalysis()
                   buffer = ""
               }
            }
            else -> {
                buffer += input
            }
        }
    }

    private fun runAnalysis(){
        var key = ""
        var err = false
        var content = buffer

        when {
            isArithmeticOp(buffer) -> {
                key = KeyTypeEnum.O_ARITMETICO.name
            }
            isLogicOp(buffer) -> {
                key = KeyTypeEnum.O_LOGICO.name
            }
            isRelationalOp(buffer) -> {
                key = KeyTypeEnum.O_RELACIONAL.name
            }
            buffer == "=" -> {
                key = KeyTypeEnum.ASIGNACION.name
            }
            isNegation(buffer) -> {
                key = KeyTypeEnum.ID.name
                content = buffer.substring(1, buffer.length)
            }
            isIntegerNumber(buffer) -> {
                key = KeyTypeEnum.ENTERO.name
            }
            isRealNumber(buffer) -> {
                key = KeyTypeEnum.REAL.name
            }
            isKeyword(buffer) -> {
                key = KeyTypeEnum.PALABRA_RESERVADA.name
            }
            isId(buffer) -> {
                key = KeyTypeEnum.ID.name
            }
            else -> {
                err = true
            }
        }

        if (!err){
            var token = Token(key, content, lineCounter, charCounter, TokenTypeEnum.SUCCESS)
            tokens.addSymbol(token)
            symbolTable.addSymbol(token)
        }

        else {
            var error = Token(key, content, lineCounter, charCounter, TokenTypeEnum.ERROR)
            errors.addSymbol(error)
        }
    }


    // Checks for Operators
    private fun isArithmeticOp(input: String): Boolean{
        for(i in ArithmeticOpEnum.values()) {
            if (input == i.sign){ return true }
        }
        return false
    }

    private fun isLogicOp(input: String): Boolean{
        for(i in LogicOpEnum.values()) {
            if (input == i.sign){ return true }
        }
        return false
    }

    private fun isRelationalOp(input: String): Boolean{
        for(i in RelationalOpEnum.values()) {
            if (input == i.sign){ return true }
        }
        return false
    }

    private fun isNegation(input: String): Boolean{
        var firstChar = input[0].toString()
        if (firstChar == "!") {
            // Add the token
            var token = Token(KeyTypeEnum.O_LOGICO.name, firstChar, lineCounter, charCounter  + 1, TokenTypeEnum.SUCCESS)
            tokens.addSymbol(token)
            symbolTable.addSymbol(token)
            var temp = input.substring(1, input.length)

            // Check in advance the Id
            if (isId(temp)) {
                return true
            }
        }
        return false
    }

    // Checks for Keywords
    private fun isKeyword(input: String): Boolean{
        for(i in KeywordEnum.values()) {
            if (input == i.word){ return true }
        }
        return false
    }

    // Checks for IDs
    private fun isId(input: String): Boolean{
        var regex = Regex("[a-z]([a-zA-Z0-9]){0,30}")
        if (regex.matches(input)){ return true }
        return false
    }

    // Checks for Numbers
    private fun isRealNumber(input: String): Boolean{
        if (input.toFloatOrNull() != null) { return true }
        return false
    }

    private fun isIntegerNumber(input: String): Boolean{
        if (input.toIntOrNull() != null){ return true }
        return false
    }

    // Checks for Separators
    private fun isPunctuationSign(input: String): Boolean{
        for(i in PunctuationEnum.values()) {
            if (input == i.sign){ return true }
        }
        return false
    }

    private fun isBlank(input: String): Boolean{
        if (input == " " || input == "\t" || input == "\n"){ return true }
        return false
    }
}