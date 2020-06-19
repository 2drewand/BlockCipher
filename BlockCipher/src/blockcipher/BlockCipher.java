/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockcipher;

import static java.lang.Integer.parseInt;

/**
 *
 * @author aand1
 */
public class BlockCipher
{
	//***********************************************************************	//Wei Lu		CS455 Cryptography and Network Security		//Midterm Project Programming Question 2	- BlockCipher.java
	//Encodes a string of ascii characters to hex format using a block cipher.
        //After the given initial vector each 8 bit block will be encrypted using the //previous 8 bits.
	//Program displays the final encrypted hex message.
	//***********************************************************************

	public static void main(String[] args) 
	{
		String message = "HTTP/1.1"; //original message
		String key = "10001001"; //initial vector
		String nextKey = "";	//key used for next block
                int[] messageASCII = new int []{0,0,0,0,0,0,0,0};
                String[] binarySwap= new String []{"0","0","0","0","0","0","0","0"};
                String[] hexSwap = new String []{"0","0","0","0","0","0","0","0"};
                String[] zero = new String[]{"0"};
		String messageBin = ""; //original message in binary
		String encryptedBin = ""; //encrypted binary message
		String encryptedHex = ""; //encrypted hex message
                /*System.out.println("***************************************************************************************\n"
                +"Andrew Cleary		CS455 Cryptography and Network Security\n"
                +"Midterm Project Programming Question 3 - BlockCipher.java\n"
                +"Encodes a string of ascii characters to hex format using a block cipher.\n"
                +"After the given initial vector each 8 bit block will be encrypted using the previous\n"
                +"8 bits.\n"
                +"Program displays the final encrypted hex message.\n"			
                +"***************************************************************************************\n");*/

                System.out.println("Encrypting \""+ message +"\" using initial vector \"" + key +"\".");
		
		//convert original message to binary
		//--------------------------------------------------
                
                for(int i=0; i<8; i++)
                {
                    messageASCII[i] = (int) message.charAt(i);
                   String temp = Integer.toBinaryString(messageASCII[i]);
                   //System.out.println(Integer.toBinaryString(messageASCII[i]));
                   while(temp.length() < 8)
                   {
                       temp = "0"+temp;
                   }
                   //System.out.println(temp);
                   messageBin += temp;
                   binarySwap[i] = (temp);
                   //System.out.print(binarySwap[i]);
                }
               // System.out.println("\n"+messageBin);
                //System.out.println(messageBin.length());
                
		//end convert to binary
		
                //---------------------------------------------------
		
		//XOR in 8 bit blocks beginning with initial vector
		//current 8 bit block becomes key for next block
		//-------------------------------------------------------
                nextKey = key;
                int ran = 0;
                for(int i = 0; i < 8; i++)
                {
                    int fakeIndex =1;
                    for (int x = 0; x < 8; x++)
                    {
                        
                        //System.out.println("BinText i at x: "+binarySwap[i].charAt(x));
                        //System.out.println("NextKey text: "+nextKey.charAt(fakeIndex-1)+"Fake index -1: "+fakeIndex);
                        //First loop gets index of array, second loop walks through characters within that array
                        if(binarySwap[i].charAt(x) == nextKey.charAt(fakeIndex-1))
                        {
                            encryptedBin += 0;
                        }
                        else
                        {
                            encryptedBin += 1;
                        }
                        fakeIndex++;
                       //System.out.println("EB: "+encryptedBin);
                    }
                    if(i < messageBin.length() - 8)
                    {
                        nextKey = encryptedBin.substring(i*8, (i*8)+8);
                    }
                    hexSwap[i] = encryptedBin.substring(i*8, (i*8)+8);
                  System.out.println("hw: "+hexSwap[i]);
                    //System.out.println("NextKey: "+nextKey);
                }
                //System.out.println("\n"+encryptedBin);
		//end xor
		//-----------------------------------------------------------------------
		
		//convert encrypted binary string to hex string
		//---------------------------------------------------------------------------
                for(int i =0; i < 8; i++)
                {
                   //System.out.println(Integer.parseInt("0000", 2));
                    //System.out.print(Integer.parseInt(hexSwap[i]));
                   
                    int temp = Integer.parseUnsignedInt(hexSwap[i], 2);
                    
                    System.out.println(temp);
                    encryptedHex += Integer.toHexString(temp);
                            
                }
		//end convert to hex
                System.out.println("Encrypted Message:\n" + encryptedHex);
                //--------------------------------------------------------------------------------
	}//end main()
}//end BlockCipher.java
