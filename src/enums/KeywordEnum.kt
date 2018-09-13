package enums

/*
* KEYWORDS:
 - principal.
 - entero.
 - real.
 - logico.
 - si.
 - mientras.
 - regresa.
 - verdadero.
 - falso.
* */

enum class KeywordEnum(val word: String) {
    MAIN("principal"),
    FUN("funcion"),
    INT("entero"),
    REAL("real"),
    BOOL("logico"),
    IF("si"),
    WHILE("mientras"),
    RETURN("regresa"),
    TRUE("verdadero"),
    FALSE("falso")
}

