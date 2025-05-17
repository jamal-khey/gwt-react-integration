import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';

// Export the React components so they can be accessed from GWT
export const renderApp = (containerId, props = {}) => {
  const container = document.getElementById(containerId);
  if (container) {
    const root = ReactDOM.createRoot(container);
    root.render(<App {...props} />);
    return true;
  }
  return false;
};

// If the script is loaded directly, render the app to the default container
if (typeof window !== 'undefined') {
  window.addEventListener('load', () => {
    const reactRoot = document.getElementById('react-root');
    if (reactRoot) {
      const root = ReactDOM.createRoot(reactRoot);
      root.render(<App />);
    }
  });
}
