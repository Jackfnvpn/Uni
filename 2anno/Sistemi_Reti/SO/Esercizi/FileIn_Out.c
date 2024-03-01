#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

int check_permession_read(struct stat File) {
    if (File.st_mode & S_IRUSR) {
        return 1;
    } else {
        return -1;
    }
}

int check_permession_write(struct stat File) {
    if (File.st_mode & S_IWUSR) {
        return 1;
    } else {
        return -1;
    }
}

int main(int argc, char *argv[]) {
    char Source[] = "./Ex_Dir/file1.txt";
    char Destination[] = "./Ex_Dir/file2.txt";
    struct stat File_Info;
    struct stat File_Info2;

    if ((stat(Source, &File_Info)) == -1 ||
        (stat(Destination, &File_Info2)) == -1) {
        fprintf(stderr, "ERRORE nell'acquisizione delle informazioni: %d", 42);
        exit(-1);
    }

    char *buffer = malloc(File_Info.st_size);

    int permession = check_permession_read(File_Info);
    int permession2 = check_permession_write(File_Info2);

    if (permession) {
        int in_fd = open(Source, O_RDONLY);
        if (in_fd < 0) {
            perror("ERRORE nell'apertura del file");
            exit(-1);
        }
        int count_read = read(in_fd, buffer, File_Info.st_size);

        if (count_read < 0) {
            perror("ERRORE nella lettura del file");
            exit(-1);
        }
        printf("%s", buffer);
    } else {
        perror("Non è consentita la lettura");
        exit(-1);
    }
    if (permession2) {
        int in_fd2 = open(Destination, O_RDWR);
        if (in_fd2 < 0) {
            perror("ERRORE nell'apertura del file");
            exit(-2);
        }
        int count_write = write(in_fd2, buffer, File_Info.st_size);
        if (count_write < 0) {
            perror("ERRORE nella scrittura del file");
            exit(-2);
        }
    } else {
        perror("Non è consentita la scrittura");
        exit(-2);
    }

    return EXIT_SUCCESS;
}
