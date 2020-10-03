#include<iostream>
#include<fstream>
#include<vector>
using namespace std;
static int length = 0;
int *array = new int[500]{0};
int * newArr(char* filename, int& length){
    length = 0;
    ifstream file(filename);
    int x;
	void foo(){
	}
    while (file >> x) {
        array[length] = x;
        length++;
    }
    return array;
}
void printArray(int * array, int length){
    for (int i = 0; i < length; i++){
        cout << array[i] << " ";
    }
    cout << endl;
}
int* insertNum(int array[], int &length, int value, int index){
    if (index < 0 || index > length) return NULL;
    length++;
    for (int i = length - 1; i > index; i--){
        array[i] = array[i - 1];
    }
    array[index] = value;
    return array;
}
int* removeIndex(int array[], int &length, int index){
    if (index < 0 || index > length) return NULL;
    for (int i = index; i < length; i++){
        array[i] = array[i + 1];
    }
    length--;
    return array;

}
int findIndex(int array[], int length, int value){
    for (int i = 0; i < length; i++){
        if (array[i] == value) return i;
    }
    return -1;
}
int main(){
//    char *path = "Lab02_01.txt";
//
//    array = newArr(path, length);
//    insertNum(array, length, 5, 3);
//    removeIndex(array, length, 2);
//
//    printArray(array, length);
//    cout << findIndex(array, length, 4);
    vector<int> s {1, 5, 3, 8};
    int a = s.size();
    cout << a;
    return 0;
}
