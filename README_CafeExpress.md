# Café Express

## Présentation

Café Express est une application Android pour prendre une commande dans un café. Elle permet de choisir les produits, de calculer le prix et de voir le récapitulatif.

## Les écrans de l’application

- **MainActivity** : écran principal. On choisit les quantités de café, de thé et de croissant, on écrit le nom du client et on voit le total.
- **Main3Activity** : écran de personnalisation. On choisit la taille du café, les suppléments, l’option à emporter et le lait. L’application calcule ensuite le prix.
- **Main2Activity** : écran récapitulatif. Il affiche le nom du client, les produits et le total. On peut revenir modifier la commande ou la confirmer.

## Ce que j’ai utilisé

Les interfaces sont faites en XML. Le code Kotlin gère les boutons, les quantités, les calculs et le passage des informations entre les écrans.

## Prix

- Café : 10 DH
- Thé : 8 DH
- Croissant : 6 DH
- Café Petit : 8 DH
- Café Moyen : 10 DH
- Café Grand : 12 DH
- Crème : +2 DH
- Cannelle : +1 DH
- À emporter : +2 DH
- Lait d’amande : +3 DH
