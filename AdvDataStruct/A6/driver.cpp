// Assignment 6
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

// driver

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
#include "HashTableCuckoo.h"
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// reading the file
// this is just reading the file to make sure it is working correctly
void readFile()
{
    std::ifstream file("data.txt");
    int key, value;
    while(file>>key>>value)
    { std::cout<<"Key = "<<key<<" value = "<<value<<std::endl; }
    file.close();
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// this is the driver menu and such 

void secondMenu(HashTable &hashTable);

void driverMenu()
{
    int command, size;
    HashTableArray *hashTable=nullptr;
    HashTableArray *hashTable2=nullptr;
    HashTableChaining*hashChain=nullptr;
    HashTableCuckoo*cuckoo=nullptr;
    do
    {
        std::cout<< "\n1) Linear Probing\n2) Quadratic Probing\n3) Separate Chaining\n4) Cuckoo Hasing\n5) Quit Program"<<std::endl;
        std::cout<<"\nPlease enter your choice: ";
        std::cin>>command;
        if(command>=1 &&command<=4)
        {
            std::cout<<"Enter size of hash table: ";
            std::cin>> size;
            if(command==1)
            { 
                hashTable= new HashTableArray(size, LINEAR_PROBING);
                std::cout<<"You have chosen linear probing"<<std::endl;

                std::cout<<"inserting data"<<std::endl;
                std::ifstream dataFile("data.txt");
                int key, value;
                while(dataFile>>key>>value)
                { 
                    std::cout<<"Key = "<<key<<" value = "<<value<<std::endl; 
                    hashTable->insert(key,value);
                }
                dataFile.close();
                hashTable->print();
                secondMenu(*hashTable);
            }
            else if(command==2)
            { 
                hashTable2= new HashTableArray(size, QUADRATIC_PROBING);
                std::cout<<"You have chosen quadradic probing"<<std::endl; 

                std::cout<<"inserting data"<<std::endl;
                std::ifstream dataFile("data.txt");
                int key, value;
                while(dataFile>>key>>value)
                { 
                    std::cout<<"Key = "<<key<<" value = "<<value<<std::endl; 
                    hashTable2->insert(key,value);
                }
                dataFile.close();
                hashTable2->print();
                secondMenu(*hashTable2);
            }
            else if(command==3)
            { 
                std::cout<<"You have chosen separate chaining"<<std::endl; 
                hashChain=new HashTableChaining(size);

                std::cout<<"inserting data"<<std::endl;
                std::ifstream dataFile("data.txt");
                int key, value;
                while(dataFile>>key>>value)
                { 
                    std::cout<<"Key = "<<key<<" value = "<<value<<std::endl; 
                    hashChain->insert(key,value);
                }
                dataFile.close();
                hashChain->print();
                //secondMenu2(*hashChain);
                secondMenu(*hashChain);
            }
            else if(command==4)
            { 
                std::cout<<"You have chosen cuckoo hashing"<<std::endl; 
                cuckoo=new HashTableCuckoo(size);
                
                std::cout<<"inserting data"<<std::endl;
                std::ifstream dataFile("data.txt");
                int key, value;
                while(dataFile>>key>>value)
                { 
                    std::cout<<"Key = "<<key<<" value = "<<value<<std::endl; 
                    cuckoo->insert(key,value);
                }
                dataFile.close();
                cuckoo->print();
                secondMenu(*cuckoo);
            }
        }
    }
    while(command!=5);
    std::cout<<"You have chosen to Quit Program"<<std::endl;
    delete hashTable;
    delete hashTable2;
    delete hashChain;
    delete cuckoo;
    std::cout<<"hashTable deleted"<<std::endl;
}

void secondMenu(HashTable &hashTable)
{
    int command2;
    do
    {
        std::cout<<"\n1) Search for entry\n2) Remove entry\n3) Print hash table\n4) return to main menu"<<std::endl;
        std::cout<<"\nPlease enter your choice: ";
        std::cin>>command2;
        std::cout<<std::endl;

        if(command2>=1 &&command2<=3)
        {
            if(command2==1)
            { 
                std::cout<<"You chose search for entry"<<std::endl; 
                int key; 
                std::cout<<"Enter key to search: ";
                std::cin>>key;
                int value =hashTable.search(key);
                if(value!=-1)
                { 
                    std::cout<<"Found key "<<key<< " with value "<<value<<std::endl; 
                    hashTable.print();
                }
                else
                { std::cout<<"Key "<<key<< " not found."<<std::endl; }
            }
            else if(command2==2)
            { 
                std::cout<<"You chose remove entry"<<std::endl; 
                int key;
                std::cout<< "Enter key to remove ";
                std::cin>>key;
                int value =hashTable.search(key);
                if(value!=-1)
                { 
                    std::cout<<"Found key "<<key<< " with value "<<value<<std::endl; 
                    hashTable.remove(key);
                    std::cout<<"Key "<<key<<" removed"<<std::endl;
                    hashTable.print();
                }
                else
                { std::cout<<"Key "<<key<< " not found."<<std::endl; }
            }
            else if(command2==3)
            { 
                std::cout<<"You chose print"<<std::endl;
                hashTable.print();
            }
        }
    } while(command2!=4);
    std::cout<<"You have chosen to return to Main Menu"<<std::endl;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

// Testing quadratic proding bc im a silly idiot
void testQuad()
{
    HashTableArray *hashTable=nullptr;
    hashTable= new HashTableArray(6,QUADRATIC_PROBING);
    // hashTable= new HashTableArray(6,LINEAR_PROBING);
    hashTable->insert(76,1);
    hashTable->insert(40,1);
    hashTable->insert(48,1);
    hashTable->insert(5,1);
    hashTable->insert(55,1);
    hashTable->print();
    delete hashTable;
}

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------

int main()
{
    std::cout<<"\n** Welcome to Assignment 6 **"<<std::endl;
    std::cout<<"Blue's Can of Who Hash!"<<std::endl;

    //readFile();
    driverMenu();
    //testQuad();
    
    return 0;
}