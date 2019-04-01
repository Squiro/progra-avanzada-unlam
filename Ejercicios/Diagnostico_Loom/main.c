#include <stdio.h>
#include <stdlib.h>

#define TAM 10
#define TRUE 1
#define FALSE 0

void inicializarRepetidas(int vec[], int size);
int chequearRepetida(int num, int vec[], int size);
void guardarRepetidas(int vec[], int cant, int num, int * index);
void mostrarVector(int vec[], int size);

/*
Consigna
Beatriz tiene una colección de estampillas. Cada estamiplla tiene un número que la identifica inequívocamente.
Para poder ampliar su colección, quiere intercambiar aquellas que tiene repetidas, siempre conservando una de repuesto por las dudas.
Escriba un programa para ayudarla: recibe un arreglo de enteros con los números de estampillas que posee (en cualquier orden)
y devuelve un arreglo conteniendo cada número de estampilla que puede intercambiar. El orden de la salida no es relevante.
Por ejemplo, si la número 1 aparece 5 veces, la salida correcta debería tener el número 1 exactamente 3 veces (5 - 2).
*/

int main()
{
    int estamp[TAM] = { 1, 7, 3, 1, 7, 4, 5, 1, 7, 1 }; // => { 1, 1, 7 }
    int repetidas[TAM];
    int size = sizeof(estamp)/sizeof(int), i, j, acu = 0, lastIndex = 0, cantRepetidas = 0;

    inicializarRepetidas(repetidas, size);

    for (i = 0; i < size; i++)
    {
        if (chequearRepetida(estamp[i], repetidas, size))
        {
            for (j = 0; j < size; j++)
                if (estamp[i] == estamp[j])
                    acu++;

            if (acu > 2)
            {
                acu -= 2;
                cantRepetidas += acu;

                guardarRepetidas(repetidas, acu, estamp[i], &lastIndex);

                /*for (k = 0; k < acu; k++)
                    repetidas[lastIndex++] = estamp[i];*/
            }

            acu = 0;
        }
    }

    printf("Cant repetidas: %d\n", cantRepetidas);
    printf("Repetidas: \n");
    mostrarVector(repetidas, cantRepetidas);

    return 0;
}

void mostrarVector(int vec[], int size)
{
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", vec[i]);
}

void guardarRepetidas(int vec[], int cant, int num, int * index)
{
    int i;

    for (i = 0; i < cant; i++)
        vec[(*index)++] = num;
}

//Retorna FALSE si la estampilla ya esta repetida

int chequearRepetida(int num, int vec[], int size)
{
    int i;

    for (i = 0; i < size; i++)
        if (vec[i] == num)
            return FALSE;

    return TRUE;
}


void inicializarRepetidas(int vec[], int size)
{
    int i;

    for (i = 0; i < size; i++)
        vec[i] = 0;
}
