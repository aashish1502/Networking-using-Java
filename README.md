# Networking-using-Java

### This Project contains all the code I can find and write based on Java Networking

The modules used in this project are:
- ServerScockets
- DatagramSockets

The aim of this project is to create a useful resource for the java Networking Library

### Currently the programs work only on a locahost or a LAN Network but I'm looking more into how to connect them through TCP/IP on the internet

## 1.Socket Networking

This is a set of a server and client code where the server listens to the client and displays the information on the terminal.

To make this code work import the code onto the machine and then run server on one and client on the other if they are connected by same WiFi or any other form of shared connection the programs can communicate with each other and send a Input Stream of data to the Server from the client

## 2. Server and Client

This directory contains 2 codes, one for server and one for client. The client and server are connected through a DatagramSockets with a locahost ip or an ip with common local area network. In this both the server and the client can communicate with each other by sending each other packets of bytes of size 1000 this also seems to not work over the internet and I will look into how to make the programs connect over the internet and work.

###### The Codes were done and pushed by using IntelliJ IDEA Community.
###### To make the codes work you need to run both server and client simultaneously and perhaps run the server first.
