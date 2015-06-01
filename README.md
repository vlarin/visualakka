# Akka Visual Extention
Netbeans Plugin for visual programming and prototyping actors in Akka with immersive possibilities

Basics
======

Install
---------

Unpack VisualAkkaPlug-in.zip and import all modules to the NetBeans

Workspace
---------------

Main workspace is graph-oriented scene, where nodes are functional objects and edges are connections between them. It can be seen in “Visual” tab of editor window for vau-file in NetBeans.

![](<http://avisualistic.com/images/git/1.png>)

*View of basic workspace*

All objects of workspace can be dragged (via dragging title part of widgets). Scene can be zoomed with Ctrl + mouse wheel.

### Navigation panel

While working with workspace of vau-file, the satellite view of this scene is displayed in NetBeans Navigation window (Window-Navigation/Ctrl + 7). Within this view bold-bordered rectangle represents viewed space. Navigation within workspace can be done via drag of this rectangle.

![](<http://avisualistic.com/images/git/2.png>)

*View of navigation panel*

Methods
------

According to the concepts of Visual Akka, each functional unit can have few methods. A special toolbar in vau-file editor is used to work with these methods. 

![](<http://avisualistic.com/images/git/3.png>)

*Vau-file toolbar (underlined part – block of tools for work with methods)*

This toolbar consists of next elements:

-   Dropdown list – displays current method name and can be used to switch between methods of current unit.

-   “New method” button – allows creating new method, method name is entered in referenced dialog window. This new method will be set as current method.

-   “Delete method” button deletes current selected method.  Attention: method deletion does not leads to deletion of all instances of this method in units of project!  (see below)

Entries
-----

Entry is functional object, which stands for representing of particular argument/constant of method. Each method has to have at least one main entry, whose name matches the name of the method; this entry can’t be deleted.

![](<http://avisualistic.com/images/git/4.png>)

*Entry view*

Entry is represented as widget, which consists of next parts:

-   Name of entry

-   Entry field, which contains entry type and connection pin.

New entry can be created via dragging “New entry” element from palette (see below) to workspace.

While selecting entry, NetBeans Properties window (Window-IDE and service-Properties/Ctrl+Shift+7) displays next properties:

| Name         | Description
---------------|---------
| Name         | Entry name
| Type         | Entry type; has to be full signature of Java class
| IsMainArg    | Is this entry main; read-only
| IsFixed      | Is this entry argument or constant (constant– true)
| DefaultValue | In case of entry is constant contains its value.

Entry can be deleted via right mouse button click and choosing “Delete” option.

Exits
------

Exit is functional object, which stands for representing of particular returning value of method.Выход – функциональный элемент, отвечающий за возвращение результата метода. In contrast to Java methods, methods of Visual Akka can have few exits; depending on the execution path some exits can get signals and some - no. 

![](<http://avisualistic.com/images/git/5.png>)

*Exit view*

Entry is represented as widget, which consists of next parts:

-   Name of exit

-   Exit field, which contains entry type and connection pin.

New entry can be created via dragging “New exit” element from palette (see below) to workspace.

While selecting exit, NetBeans Properties window (Window-IDE and service-Properties/Ctrl+Shift+7) displays next properties:

| Name     | Description
-----------|---------
| Name     | Name of exit
| Type     | Entry type; has to be full signature of Java class

Exit can be deleted via right mouse button click and choosing “Delete” option.

Method instances
------------------

Method instance is functional element, which represents instantiated link to unit method. According to the concepts of Akka method instance is the instance of actor class. Methods instances are main compositional elements of Visual Akka.

![](<http://avisualistic.com/images/git/6.png>)

*Method instance view*

Method instance is represented as widget, which consists of next parts:

-   Method name, which this instance refers on

-   Router field which briefly displays information about linked router(if it exists), or informs about its absence

-   One or more argument field, which contains connection pin, argument name and type 

-   Few constant fields(if they exists), which contain constant name, type and set 

-   Few exits fields(if they exists), which contain exit name, type and connection pin 

Method instance can be created via dragging method name node from hierarchical tree view of Units window to workspace (Units window is called via Window-UnitsView menu)

![](<http://avisualistic.com/images/git/7.png>)

*View of “Units” window*

While selecting method instance, NetBeans Properties window (Window-IDE and service-Properties/Ctrl+Shift+7) displays next properties:

| Name                            | Description
----------------------------------|---------
| Default values                  | Part with key-value pairs, which stands for setting constants
| Supervising                     | Part for setting up supervising
| Supervisor strategy             | Supervisor strategy type  (see akka reference)
| Within time range               | Time interval between actor restarts in case of exception
| Max Number of reties            | Maximal number of reties 
| Routing                         | Part for setting up routing
| Enabled                         | Is the routing enabled for this instance
| Routing logic                   | Routing logic type  (see akka reference)
| Stretching                      | Is flexible (stretched) routing enabled (not yet implemented)
| Routees amount(min + max routes)| Routees amount (minimal and maximal amount)
| Max mailbox capacity            | If mailbox capacity is greater than this value then flexible router stretches

In contrast to the entries and exits, method instances can be copied to another method of unit (via context menu); in that case, messages will be sent exactly to this instance. In addition, like other workspace object, method instance can be deleted through context menu. 

User code blocks
-----------------------

User code blocks represent interface for custom user code execution within Visual Akka units. Creation of these objects will cause generation of appropriate stub classes (with fully configured routing). User should implement custom logic within these generated classes. User code blocks can be created via dragging “New User Code Block” element from palette (see below) to workspace. A wizard will be called, and during work with this wizard user will have to create method signature. Method name, Entries and Outputs are main fields to be filled.

![](<http://avisualistic.com/images/git/8.png>)

*User code block view*

There is no difference between work with User Code Blocks and Method Instances in the rest of cases. Properties of routing, supervising and work with the constants remain the same.

Palette
-------

Palette – window, which is used for quick and  easy creation of new Visual Akka workspace objects. All objects are created via dragging corresponding elements to workspace. At the moment, the palette contains only the section "New", which contains three points: "Entry", "Exit", "User code block"

![](<http://avisualistic.com/images/git/9.png>)

*Palette window view*

States
---------

States – FSM implementation. Allows to create state-related transitions, and to change the state of a unit instance.

### States window

States window (Window-StatesView) allows to monitor, create and delete states for current unit. A state setter, which sets unit state to corresponding, can be created via dragging it from window view to workspace. 

![](<http://avisualistic.com/images/git/10.png>)

*View of “States” window*

### State setters

State setter is functional element which stands for setting unit state to corresponding. Can be created via dragging element of list of “States” window to workspace. 

![](<http://avisualistic.com/images/git/11.png>)

*State setter view*

State setter is represented as widget, which consists of next parts:

-   Name of state

-   Connection pin

Connections
-----

Connections – lines, which connect source and target pins of functional objects of workspace. Determine the appropriate data flows. Each connection can have state filter, which can be set from context (submenu “Set State”)

There is also field extraction (if output type is too complex and contains subfields) (context menu-submenu ”Extract field”) (at the moment function is not available)

Generated code
===================
```java
package test;

import org.vap.core.Flow;

/**
* User code logic implementation
*/

public class PowImpl extends test.Pow.UCBAbstract {

  @Override
  public void Power(Double Power, Double Base, Flow\<Double\> Result) {
    Double res = Math.pow(Power, Base);
    System.out.println("Result is - " + res);
    Result.send(res);
  }
}
```
All user code blocks are translated into appropriate java code stubs after the code generation (happens on save action)

Practical tips
=====================

Project creation
----------------

There is special project type “Visual Akka project” which contains all necessary dependencies. This project can be created via “Create project-JavaEE-VisualAkkaProject”. Or it can be done manually in java project via adding all neccessary Visual Akka libraries (dependencies)
Basic dependencies are akka-actor and akka-visual.

Unit file creation
---------------------

New Visual Akka unit file (“vau”-file) can be created via “File-Create file(Ctrl-N)-Visual Akka- Visual Akka Unit”.

# Licence
Akka Visual Extention is Open Source and available under the Apache 2 License.

Vladislav Larin, vlarinmain@gmail.com

Oleg Bantysh, iambantysh@gmail.com

Serhii Biletskyi, shutclare@gmail.com

All rights reserved.
