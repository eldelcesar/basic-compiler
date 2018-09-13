import enums.TokenTypeEnum

class Token(private val keySym: String, private val contentSym: String, private var lineNumber: Int, private var charNumber: Int, private var tokenType: TokenTypeEnum) {

    fun getKey(): String { return keySym }

    fun getContent(): String { return contentSym }

    fun getLn(): Int { return lineNumber }

    fun getCn(): Int { return charNumber }

    fun getTokenT(): TokenTypeEnum { return tokenType }

    override fun toString(): String {
        var str: String = "KEY: " + this.keySym + "\nVALUE: " + this.contentSym + "\nLINE NUMBER: " + this.lineNumber + "\nCHAR NUMBER: " + this.charNumber
        str += "\n–––––––––––––––––––\n\n"

        return str
    }
}