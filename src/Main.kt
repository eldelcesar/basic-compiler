
import java.io.File

fun main(args: Array<String>) {

    println("Running Lex Analyser...")
    var lex: LexAnalyser = LexAnalyser()
    var lexResult: MutableList<SymbolsTable> = lex.runLexAnalysis("./resources/Archivo_Fuente.txt")

    if (lexResult.size > 1) {
        print("\n––––––––––––––––– TOKENS –––––––––––––––––\n")
        print(lexResult[0])
        print("\n––––––––––––––––– SYMBOLS –––––––––––––––––\n")
        print(lexResult[1])
    }
    else {
        print("\n––––––––––––––––– ERRORS –––––––––––––––––\n")
        print(lexResult[0])
    }
}
