package com.google.gwt.sample.hello.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.core.client.ScriptInjector;
import com.google.gwt.core.client.Callback;

/**
 * A wrapper for React components to be used in GWT.
 */
public class ReactWrapper {
    
    /**
     * Loads the React bundle script.
     * 
     * @param callback Callback to be executed after script is loaded
     */
    public static void loadReactScript(final Callback<Void, Exception> callback) {
        ScriptInjector.fromUrl("react-app/dist/bundle.js")
            .setCallback(new Callback<Void, Exception>() {
                @Override
                public void onFailure(Exception reason) {
                    if (callback != null) {
                        callback.onFailure(reason);
                    }
                }

                @Override
                public void onSuccess(Void result) {
                    if (callback != null) {
                        callback.onSuccess(result);
                    }
                }
            })
            .setWindow(ScriptInjector.TOP_WINDOW)
            .inject();
    }
    
    /**
     * Creates a GWT widget that contains a React component.
     * 
     * @param containerId The ID to use for the container element
     * @param message Optional message to pass to the React component
     * @return A GWT widget containing the React component
     */
    public static Widget createReactWidget(String containerId, String message) {
        // Create a container for the React component
        HTMLPanel container = new HTMLPanel("<div id='" + containerId + "'></div>");
        
        // After the widget is attached to the DOM, render the React component
        container.addAttachHandler(event -> {
            if (event.isAttached()) {
                renderReactComponent(containerId, message);
            }
        });
        
        return container;
    }
    
    /**
     * Renders a React component into the specified container.
     * 
     * @param containerId The ID of the container element
     * @param message Optional message to pass to the React component
     */
    private static native void renderReactComponent(String containerId, String message) /*-{
        if ($wnd.ReactComponents && $wnd.ReactComponents.renderApp) {
            var props = {};
            if (message) {
                props.message = message;
            }
            $wnd.ReactComponents.renderApp(containerId, props);
        } else {
            console.error("React components not loaded yet");
        }
    }-*/;
    
    /**
     * Calls the increment counter function in the React component.
     * 
     * @return The new counter value
     */
    public static native int incrementCounter() /*-{
        if ($wnd.incrementCounter) {
            return $wnd.incrementCounter();
        }
        return -1;
    }-*/;
    
    /**
     * Sets a message in the React component.
     * 
     * @param message The message to set
     */
    public static native void setMessage(String message) /*-{
        if ($wnd.setMessage) {
            $wnd.setMessage(message);
        }
    }-*/;
    
    /**
     * Sets a callback function to be called when a message is received from React.
     * 
     * @param callback The callback function
     */
    public static native void setMessageCallback(MessageCallback callback) /*-{
        $wnd.messageCallback = function(message) {
            callback.@com.google.gwt.sample.hello.client.ReactWrapper.MessageCallback::onMessage(Ljava/lang/String;)(message);
        };
    }-*/;
    
    /**
     * Interface for callback when a message is received from React.
     */
    public interface MessageCallback {
        void onMessage(String message);
    }
}
