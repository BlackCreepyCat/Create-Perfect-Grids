; ----------------------------------------
; Name : Create Perfect Grids
; Date : (C)2025
; Site : https://github.com/BlackCreepyCat
; ----------------------------------------


Type Grille
    Field Px, Py, Tx, Ty
    Field cols, rows
End Type

; Exemple d'utilisation
Graphics 800, 600, 32, 2

Local grille.Grille = New Grille
grille\Px = 100
grille\Py = 100
grille\Tx = 730
grille\Ty = 400
grille\cols = 10
grille\rows = 10

; Exemple de case à colorier (par exemple, la case à la colonne 3, ligne 5)
Global colToColor = 3
Global  rowToColor = 5

While Not KeyDown(1)
    Cls
    DrawGrid(grille)
	
	ColorierCase(grille, colToColor, rowToColor, 255 , 0 , 0)
	
	If MouseDown(2) Then
		
		grille\Px = MouseX()
		grille\Py= MouseY()
		
		
	EndIf
	
    Flip
Wend
End

Function DrawGrid(grille.Grille)
    If grille\cols < 2 Then grille\cols = 2
    If grille\rows < 2 Then grille\rows = 2
	
    Local dx# = Float(grille\Tx - grille\Px) / grille\cols
    Local dy# = Float(grille\Ty - grille\Py) / grille\rows
	
    ; Détection précise de la case sous la souris
    Local mx# = MouseX()
    Local my# = MouseY()
	
    ; Tracer les lignes verticales
    Color 255, 255, 255  ; Blanc
    For i = 0 To grille\cols
        Line grille\Px + i * dx, grille\Py, grille\Px + i * dx, grille\Ty
    Next
	
    ; Tracer les lignes horizontales
    For j = 0 To grille\rows
        Line grille\Px, grille\Py + j * dy, grille\Tx, grille\Py + j * dy
    Next
	
    ; Détection précise de la case sous la souris pour l'affichage
    If mx >= grille\Px And mx < grille\Tx And my >= grille\Py And my < grille\Ty Then
        Local col = Floor((mx - grille\Px) / dx)
        Local row = Floor((my - grille\Py) / dy)
		
		ColorierCase(grille, col, row, 255 , 0 , 0)
    EndIf
	
End Function

Function ColorierCase(grille.Grille, col, row,  R,G,B )
    ; Calculer la position de la case spécifiée sans passer DX, DY
    Local dx# = Float(grille\Tx - grille\Px) / grille\cols
    Local dy# = Float(grille\Ty - grille\Py) / grille\rows
	
    ; Calculer la position exacte de la case à colorier
    Local xPos# = grille\Px + col * dx
    Local yPos# = grille\Py + row * dy
	
	
    ; Appliquer la couleur à la case spécifiée
    Color R,G,B
    Rect xPos + 1 , yPos + 1 , dx - 1, dy - 1 , True
	
	Color 0, 0, 0  ; Noir pour le texte
	Text xPos+2, yPos+2 , "C:" + col + "/R:" + row
End Function

;~IDEal Editor Parameters:
;~C#Blitz3D