import { Link } from 'react-router-dom';

export default function Home() {
  const token = localStorage.getItem('token');
  const targetRoute = token ? '/chat' : '/login';

  return (
    <section className="home">
      <div className="home-content">
        <div className="hero-section">
          <h1 className="hero-title">Ask Lord Krishna</h1>
          <p className="hero-subtitle">Discover ancient wisdom for modern life</p>
          <div className="hero-description">
            <p>Seek guidance from the timeless teachings of the Bhagavad Gita.</p>
            <p>Find answers to life's deepest questions through divine wisdom.</p>
          </div>
          <div style={{ marginTop: '2rem' }}>
            <Link to={targetRoute} className="link-btn" style={{ fontSize: '1.1rem', padding: '0.9rem 2rem' }}>
              {token ? 'Go to Chat 🪷' : 'Seek Wisdom Now 🪷'}
            </Link>
          </div>
        </div>

        <div className="feature-cards">
          <div className="feature-card">
            <div className="card-icon">📖</div>
            <h3>Gita Wisdom</h3>
            <p>Get answers rooted in the philosophy and shlokas of the Bhagavad Gita.</p>
          </div>
          <div className="feature-card">
            <div className="card-icon">🕊️</div>
            <h3>Inner Peace</h3>
            <p>Navigate modern dilemmas of duty, mind control, karma, and devotion with clarity.</p>
          </div>
          <div className="feature-card">
            <div className="card-icon">⚡</div>
            <h3>Instant Guidance</h3>
            <p>Powered by AI attuned to the gentle, compassionate voice of Lord Krishna.</p>
          </div>
        </div>
      </div>
    </section>
  );
}
