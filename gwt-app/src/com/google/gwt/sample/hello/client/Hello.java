/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.gwt.sample.hello.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.Callback;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTML;

/**
 * HelloWorld application with React integration.
 */
public class Hello implements EntryPoint {

  public void onModuleLoad() {
    // Create a panel to organize our widgets
    final VerticalPanel mainPanel = new VerticalPanel();
    mainPanel.setSpacing(10);
    mainPanel.setWidth("100%");
    
    // Add a title
    HTML title = new HTML("<h1>GWT with React Integration</h1>");
    mainPanel.add(title);
    
    // Add a description
    Label description = new Label("This demonstrates how to integrate React components into a GWT application.");
    mainPanel.add(description);
    
    // Add a separator
    HTML separator = new HTML("<hr/>");
    mainPanel.add(separator);
    
    // Add the original GWT button
    Button gwtButton = new Button("GWT Button", new ClickHandler() {
      public void onClick(ClickEvent event) {
        System.out.println("GWT button clicked");
        Window.alert("Hello from GWT!");
      }
    });
    mainPanel.add(gwtButton);
    
    // Add another separator
    HTML separator2 = new HTML("<hr/>");
    mainPanel.add(separator2);
    
    // Load React script and add React component
    final Label loadingLabel = new Label("Loading React components...");
    mainPanel.add(loadingLabel);
    
    ReactWrapper.loadReactScript(new Callback<Void, Exception>() {
      @Override
      public void onFailure(Exception reason) {
        loadingLabel.setText("Failed to load React components: " + reason.getMessage());
      }

      @Override
      public void onSuccess(Void result) {
        loadingLabel.setText("React components loaded successfully!");
        
        // Create a React component widget
        Widget reactWidget = ReactWrapper.createReactWidget("react-container", "Hello from GWT!");
        mainPanel.add(reactWidget);
        
        // Add a button to interact with the React component
        Button interactButton = new Button("Increment React Counter", new ClickHandler() {
          public void onClick(ClickEvent event) {
            int newCount = ReactWrapper.incrementCounter();
            Window.alert("React counter incremented to: " + newCount);
          }
        });
        mainPanel.add(interactButton);
        
        // Add a button to send a message to the React component
        Button messageButton = new Button("Send Message to React", new ClickHandler() {
          public void onClick(ClickEvent event) {
            String message = Window.prompt("Enter a message for the React component:", "");
            if (message != null && !message.isEmpty()) {
              ReactWrapper.setMessage(message);
            }
          }
        });
        mainPanel.add(messageButton);
        
        // Set up callback for messages from React
        ReactWrapper.setMessageCallback(new ReactWrapper.MessageCallback() {
          @Override
          public void onMessage(String message) {
            Window.alert("Message from React: " + message);
          }
        });
      }
    });
    
    // Add the main panel to the page
    RootPanel.get().add(mainPanel);
  }
}
