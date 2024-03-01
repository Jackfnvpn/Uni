#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <unistd.h>

#define TRUE 1
#define BUFFER_SIZE 50

int main(int argc, char *argv[]) {
    struct stat fileInfo;
    stat(argv[1], &fileInfo);
    int in_fd;
    int read_count;
    char buffer[BUFFER_SIZE];
    printf("%d", stdout);

    if (fileInfo.st_mode & S_IRUSR) {
        in_fd = open(argv[1], O_RDONLY);
        if (in_fd < 0) {
            fprintf(stderr, "ERRORE", argv[1]);
            exit(1);
        } else {
            if (lseek(in_fd, 2, SEEK_CUR) < 0) {
                fprintf(stderr, "ERROR");
                exit(1);
            }
            while ((read_count = read(in_fd, buffer, BUFFER_SIZE)) > 0) {
                write(STDOUT_FILENO, buffer, read_count);
            }
            if (read_count < 0) {
                perror("Error reading file");
                exit(1);
            }
        }
    }

    close(in_fd);
    return EXIT_SUCCESS;
}
