import java.io.FileNotFoundException

// All code is available on https://github.com/eldelcesar/basic-compiler
// Example source file at ./resources/Archivo_Fuente.txt

fun main(args: Array<String>) {


    try {
        print("Welcome to the program compiler!\nEnter the file (with path) of the file: \n")
        val input = readLine()!!

        var lex = LexAnalyser()
        var lexResult = lex.runLexAnalysis(input)
        println("Running Lex Analyser on '$input' file...")

        if (lexResult.size > 1) {
            print("\n––––––––––––––––– TOKENS –––––––––––––––––\n")
            print(lexResult[0])
            print("\n\n––––––––––––––––– SYMBOLS –––––––––––––––––\n")
            print(lexResult[1])
            print("SUCCESSFUL LEX ANALYSIS")
        }
        else {
            print("\n––––––––––––––––– ERRORS –––––––––––––––––\n")
            print(lexResult[0])
            print("ERROR: FAILED LEX ANALYSIS")
        }
    }
    catch (err: FileNotFoundException){

        print("ERROR: FILE NOT FOUND")
    }
}
