import React, { useState } from 'react';

const App = (props) => {
  const [count, setCount] = useState(0);
  
  // This method can be called from GWT
  window.incrementCounter = () => {
    setCount(prevCount => prevCount + 1);
    return count + 1;
  };

  // This method can be called from GWT
  window.setMessage = (message) => {
    if (window.messageCallback) {
      window.messageCallback(message);
    }
  };

  return (
    <div style={{ 
      padding: '20px', 
      border: '1px solid #ddd', 
      borderRadius: '8px',
      fontFamily: 'Arial, sans-serif'
    }}>
      <h2>React Component in GWT</h2>
      <p>This is a React component rendered inside a GWT application.</p>
      <p>Count: <strong>{count}</strong></p>
      <button 
        onClick={() => setCount(count + 1)}
        style={{
          padding: '8px 16px',
          backgroundColor: '#4285f4',
          color: 'white',
          border: 'none',
          borderRadius: '4px',
          cursor: 'pointer'
        }}
      >
        Increment
      </button>
      {props.message && (
        <div style={{ marginTop: '20px' }}>
          <p>Message from GWT: <strong>{props.message}</strong></p>
        </div>
      )}
    </div>
  );
};

export default App;
