mdoc-ejb
========

If you get "javax.naming.NameNotFoundException" for Message-Driven Bean, add following text to <JBoss installation folder>\server\default\deploy\messaging\destinations-service.xml : 

	    <mbean code="org.jboss.jms.server.destination.QueueService"    
          name="jboss.messaging.destination:service=Queue,name=myqueue"    
          xmbean-dd="xmdesc/Queue-xmbean.xml">    
          <annotation>@org.jboss.system.deployers.managed.ManagementObjectClass(code=org.jboss.jms.server.destination.QueueServiceMO)</annotation>          
          <depends optional-attribute-name="ServerPeer">jboss.messaging:service=ServerPeer</depends>    
          <depends>jboss.messaging:service=PostOffice</depends>    
     </mbean>  
