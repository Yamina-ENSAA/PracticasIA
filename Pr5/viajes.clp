;Práctica 6 - Inteligencia Artificial
;Manuel Sanchez, Pablo Mac-Veigh

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Enumeraciones
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; Season
;;
;; Rangos estacionales basados en http://www.almanac.com/content/first-day-seasons
;;
;; 0: primavera (90 dÃ­as 82 - 172)
;; 1: verano    (90 dÃ­as 173 - 263)
;; 2: otoÃ±o     (90 dÃ­as 264 - 354)
;; 3: invierno  (91 dÃ­as 355 - 81)

;; hobby / theme
;;
;; 0: Deportes
;; 1: Aventura
;; 2: Cultura
;; 3: Turismo
;; 4: GastronomÃ­a

;; companion 
;;
;; 0: solo
;; 1: familia
;; 2: amigos

;; languages
;;
;; 0: EspaÃ±ol
;; 1: InglÃ©s
;; 2: FrancÃ©s
;; 3: Chino
;; 4: AlemÃ¡n
;; 5: Indio
;; 6: PortuguÃ©s

;Definition del template del usuario
(deftemplate user
    (slot season(type number)); Ã‰poca del aÃ±o en la que el usuario quiere viajar (ENUMERADO)
    (slot budget(type number)); Presupuesto
    (slot hobby(type number)); AficiÃ³n (ENUMERADO). TODO: multislot varias aficiones
    (slot age(type number)); Edad
    (slot companion(type number)); AcompaÃ±antes (ENUMERADO)
    (slot exotic(type number)); Busca un viaje exÃ³tico? (TRUE:1, FALSE:0)
    (slot languages(type number)); Idioma (ENUMERADO)
)

;DeficiciÃ³n template viaje
(deftemplate travel
	(slot begins(type number)); DÃ­a que empieza el viaje (Ã�ndice 0-364 representando un dÃ­a del aÃ±o)
    (slot ends(type number)); DÃ­a que termina el viaje (Ã�ndice 0-364 representando un dÃ­a del aÃ±o)
    (slot theme(type number)); TemÃ¡tica del viaje (Deporte, turismo, aventura, etc) (ENUMERADO)
    ;TODO ofertas especiales
    (slot language(type number)); Idioma del sitio de destino (Ver languages en user) TODO: multislot varios idiomas
    (slot company(type STRING)); CompaÃ±Ã­a
    (slot name(type STRING)); TÃ­tulo
    (slot description(type STRING)); DescripciÃ³n del viaje     
    ;Añadir continente
    ;Añadir 'dureza' del viaje? (en familia o no)
)

;Definicion del template recomendaciÃ³n
(deftemplate recommendation
    (slot name(type STRING))
    (slot description(type STRING))
)

;Asertación de hechos: Usuario de prueba
(deffacts usr
    (user (season 0) (budget 5000) (hobby 2) (age 26) (companion 0) (exotic 1) (languages 3))
    )

;Asertación de hechos: Viajes
(deffacts trvl
    ;(travel (begins) (ends) (theme) (language) (company) (name) (description))
    (travel (begins 20) (ends 30) (theme 2) (language 3) (company "Air China") 
     	(name "Viaje al Tibet") (description "Un viaje al Tibet es todo lo que necesita alguien que busca cultura como tu"))
    )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; RECOMMENDATION MODULE
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmodule travelRecommendation)

;Posible regla sobre un viaje exótico:
;Si el usuario ha seleccionado un viaje exótico - y no habla el idioma del sitio se considera exótico?
;Solo es una idea y ejemplo.
(deffunction possibleExoticRule (?travelLanguage ?userExotic ?userLangage)
    (return (and (eq ?userExotic 1) (neq ?travelLangage ?userLangage)))
    )

(reset)
(run)
(facts)
