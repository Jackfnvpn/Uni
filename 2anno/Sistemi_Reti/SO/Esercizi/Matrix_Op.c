#include <linux/limits.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

#define MAX_SIZE 3
#define PIPE_WR 1
#define PIPE_RD 0

int *matrixp1(int m[MAX_SIZE][MAX_SIZE], int *row);
int *matrixp2(int m[MAX_SIZE][MAX_SIZE], int *row);
int *matrixp3(int m[MAX_SIZE][MAX_SIZE], int *row);

int *matrixp1(int m[MAX_SIZE][MAX_SIZE], int *row) {

  int *result_column = malloc(sizeof(int) * MAX_SIZE);
  for (int r = 0; r < MAX_SIZE; r++) {
    int sum = 0;
    for (int j = 0; j < MAX_SIZE; j++) {
      sum += row[j] * m[j][r];
    }
    result_column[r] = sum;
  }
  return result_column;
}

int *matrixp3(int m[MAX_SIZE][MAX_SIZE], int *row) {

  int *result_column = malloc(sizeof(int) * MAX_SIZE);
  for (int r = 0; r < MAX_SIZE; r++) {
    int sum = 0;
    for (int j = 0; j < MAX_SIZE; j++) {
      sum += row[j] * m[j][r];
    }
    result_column[r] = sum;
  }
  return result_column;
}
int *matrixp2(int m[MAX_SIZE][MAX_SIZE], int *row) {
  int *result_column = malloc(sizeof(int) * MAX_SIZE);
  for (int r = 0; r < MAX_SIZE; r++) {
    int sum = 0;
    for (int j = 0; j < MAX_SIZE; j++) {
      sum += row[j] * m[j][r];
    }
    result_column[r] = sum;
  }
  return result_column;
}

int main(int argc, char *argv[]) {
  int p1, p2;

  int pipe1[2], pipe2[2];

  if ((pipe(pipe1)) == -1 || (pipe(pipe2) == -1)) {
    perror("ERRORE nella creazione delle pipe");
    exit(-1);
  }

  int Matrix1[MAX_SIZE][MAX_SIZE] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

  int Matrix2[MAX_SIZE][MAX_SIZE] = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};

  int Matrix3[MAX_SIZE][MAX_SIZE];

  if ((p1 = fork()) == -1) {
    perror("Errore nella fork");
    exit(-1);
  }

  if (p1 == 0) {
    // printf("ciao");
    int *column1 = malloc(sizeof(int) * MAX_SIZE);
    for (int i = 0; i < MAX_SIZE; i++) {
      column1[i] = Matrix1[0][i];
    }
    int *first_column_m = matrixp1(Matrix2, column1);

    close(pipe1[PIPE_RD]);

    int count_write =
        write(pipe1[PIPE_WR], first_column_m, sizeof(int) * MAX_SIZE);

    if (count_write < 0) {
      perror("ERRORE nella scrittura della pipe");
      exit(-1);
    }

    close(pipe1[PIPE_WR]);
  } else {
    if ((p2 = fork()) == -1) {
      perror("Errore nella fork"), exit(-1);
    }
    if (p2 == 0) {
      int *column2 = malloc(sizeof(int) * MAX_SIZE);
      for (int i = 0; i < MAX_SIZE; i++) {
        column2[i] = Matrix1[1][i];
      }
      int *second_column_m = matrixp2(Matrix2, column2);

      /*for (int i = 0; i < 3; i++) {
        printf("%d", second_column_m[i]);
      }*/
      close(pipe2[PIPE_RD]);

      int count_write2 =
          write(pipe2[PIPE_WR], second_column_m, sizeof(int) * MAX_SIZE);

      close(pipe2[PIPE_WR]);
    } else {
      int *column3 = malloc(sizeof(int) * MAX_SIZE);
      for (int i = 0; i < MAX_SIZE; i++) {
        column3[i] = Matrix1[2][i];
      }
      int *third_column_m = matrixp3(Matrix2, column3);

      close(pipe1[PIPE_WR]);
      close(pipe2[PIPE_WR]);

      int *first_column_p = malloc(sizeof(int) * MAX_SIZE);
      int second_column_p[MAX_SIZE];

      int count_read =
          read(pipe1[PIPE_RD], first_column_p, sizeof(int) * MAX_SIZE);

      close(pipe1[PIPE_RD]);

      int count_read2 =
          read(pipe2[PIPE_RD], second_column_p, sizeof(int) * MAX_SIZE);
      close(pipe2[PIPE_RD]);

      for (int i = 0; i < MAX_SIZE; i++) {
        Matrix3[0][i] = first_column_p[i];
        Matrix3[1][i] = second_column_p[i];
        Matrix3[2][i] = third_column_m[i];
      }
      for (int r = 0; r < MAX_SIZE; r++) {
        printf("\n");
        for (int c = 0; c < MAX_SIZE; c++) {
          printf(" %4.2d ", Matrix3[r][c]);
        }
      }
      printf("\n");
    }
  }
  // waitpid(p1, NULL, 0);
  // waitpid(p2, NULL, 0);
  return EXIT_SUCCESS;
}
