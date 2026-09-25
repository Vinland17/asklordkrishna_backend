import { useState, useRef, useEffect } from 'react';
import api from '../services/api';

export default function Chat() {
  const [question, setQuestion] = useState('');
  const [messages, setMessages] = useState([]);
  const [sending, setSending] = useState(false);
  const bottomRef = useRef(null);

  /* auto-scroll to latest message */
  useEffect(() => {
    bottomRef.current?.scrollIntoView({ behaviour: 'smooth' });
  }, [messages]);

  /* send message */
  const ask = async () => {
    if (!question.trim()) return;
    setSending(true);
    setMessages(m => [...m, { from: 'user', text: question }]);
    setQuestion('');

    /* mock / real call */
    try {
      const { data } = await api.post('/chat/ask', { question });
      setMessages(m => [...m, { from: 'krishna', text: data.answer }]);
    } catch {
      setMessages(m => [
        ...m,
        { from: 'krishna', text: '🙏 Sorry, something went wrong. Try again.' },
      ]);
    } finally {
      setSending(false);
    }
  };

  const handleKey = e => {
    if (e.key === 'Enter' && !e.shiftKey) {
      e.preventDefault();
      ask();
    }
  };

  return (
    <section className="chat-page">
      {/* CHAT WINDOW */}
      <div className="chat-window">
        {messages.map((m, i) =>
          m.from === 'krishna' ? (
            <div key={i} className="msg-row left">
              <div className="sender">Krishna</div>
              <div className="bubble krishna-bubble">{m.text}</div>
            </div>
          ) : (
            <div key={i} className="msg-row right">
              <div className="bubble user-bubble">{m.text}</div>
            </div>
          )
        )}
        <div ref={bottomRef} />
      </div>

      {/* INPUT BAR */}
      <div className="chat-input-bar">
        <textarea
          value={question}
          onChange={e => setQuestion(e.target.value)}
          onKeyDown={handleKey}
          placeholder="Type a message…"
        />
        <button
          className="send-btn"
          onClick={ask}
          disabled={sending || !question.trim()}
        >
          {/* WhatsApp-style paper-plane SVG */}
          <svg
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            strokeWidth="2"
            strokeLinecap="round"
            strokeLinejoin="round"
          >
            <path d="M22 2 11 13" />
            <polygon points="22 2 15 22 11 13 2 9 22 2" />
          </svg>
        </button>
      </div>
    </section>
  );
}
