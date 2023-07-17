# ScalaPub

A sample implemention of ActivityPub in scala. The goal is to mirror 
[tOkeshu/activitypub-example](https://github.com/tOkeshu/activitypub-example/tree/master).


Features
----

Outbox: 

- [ ] Accept Create activities
- [ ] Accept Follow activities
- [ ] Accept Like activities
- [ ] Accept non-activity objects and convert them to a create activity

Delivery:

- [ ] Handle `to` audience
- [ ] Handle `cc` audience
- [ ] Handle `bto`  and `bcc`
- [ ] Handle `audience` audience

Inbox:

- [ ] Accept Create activities
- [ ] Accept Follow activities
- [ ] Accept Like activities

Getting Started
-----

This lib is built with JDK 17. It may not be required, but it is what is in the flake. 

You have to have scala and sbt installed. 

    $ git clone https://github.com/TheDrawingCoder-Gamer/ScalaPub

Run the server

    $ sbt run

You can also use flake if you have nixpkgs installed or are running on nixos. 

