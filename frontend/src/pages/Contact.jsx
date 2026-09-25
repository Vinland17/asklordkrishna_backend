import { useState, useCallback } from 'react';

export default function Contact() {
  const [showEmail, setShowEmail] = useState(false);

  /* toggle modal */
  const toggleEmail = useCallback(() => setShowEmail(p => !p), []);

  /* copy to clipboard */
  const copyEmail = () => {
    navigator.clipboard.writeText('vineetmm16@gmail.com')
      .then(() => alert('Email copied to clipboard!'))
      .catch(() => alert('vineetmm16@gmail.com'));
  };

  return (
    <section className="contact-page">
      {/* ───────── email pop-up ───────── */}
      {showEmail && (
        <div className="email-popup-overlay" onClick={toggleEmail}>
          <div className="email-popup" onClick={e => e.stopPropagation()}>
            <div className="email-popup-header">
              <h3>My Email</h3>
              <button className="close-btn" onClick={toggleEmail}>×</button>
            </div>
            <div className="email-popup-content">
              <div className="email-display">vineetmm16@gmail.com</div>
              <button onClick={copyEmail} className="copy-btn">Copy</button>
            </div>
          </div>
        </div>
      )}

      {/* ───────── main contact page ───────── */}
      <div className="contact-container">
        <h2>Contact Us</h2>
        <p className="contact-intro">
          Have questions, suggestions, or feedback?  I'd love to hear from you!
        </p>

        {/* sections */}
        <div className="contact-content">
          <div className="contact-info">
            <h3>Get in Touch</h3>
            <p>General enquiries, feature ideas or just a friendly hello.</p>

            <div className="contact-details">
              {/* General */}
              <div className="contact-item">
                <h4>General Inquiries</h4>
                <p>Anything related to the platform or its features.</p>
                <div className="btn-row">
                  <a
                    href="https://in.linkedin.com/in/vineet-m-kelaskar-7962b4275"
                    className="link-btn"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    LinkedIn
                  </a>
                  <button type="button" onClick={toggleEmail} className="link-btn">
                    Gmail
                  </button>
                </div>
              </div>

              {/* Tech */}
              <div className="contact-item">
                <h4>Technical Feedback</h4>
                <p>Found a bug or have a tech suggestion?</p>
                <div className="btn-row">
                  <a
                    href="https://in.linkedin.com/in/vineet-m-kelaskar-7962b4275"
                    className="link-btn"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    LinkedIn
                  </a>
                  <button type="button" onClick={toggleEmail} className="link-btn">
                    Gmail
                  </button>
                </div>
              </div>

              {/* Collaboration */}
              <div className="contact-item">
                <h4>Collaboration</h4>
                <p>Interested in collaborating or learning more about the project?</p>
                <div className="btn-row">
                  <a
                    href="https://in.linkedin.com/in/vineet-m-kelaskar-7962b4275"
                    className="link-btn"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    LinkedIn
                  </a>
                  <button type="button" onClick={toggleEmail} className="link-btn">
                    Gmail
                  </button>
                  <a
                    href="https://www.instagram.com/vineet_m_kelaskar?igsh=amd6Z21pNnoyYzZ4"
                    className="link-btn"
                    target="_blank"
                    rel="noopener noreferrer"
                  >
                    Instagram
                  </a>
                </div>
              </div>
            </div>
          </div>

          {/* project info */}
          <div className="contact-note">
            <h3>Project Information</h3>
            <p>
              This is an educational, experimental project that brings ancient wisdom
              to a modern audience using AI.It is still in its initial stages of development and updates will take place. The code is open for anyone curious about the
              implementation.
            </p>
            <a
              href="https://github.com/Vinland17/asklordkrishna_backend.git"
              className="link-btn"
              target="_blank"
              rel="noopener noreferrer"
            >
              GitHub Repository
            </a>
          </div>
        </div>
      </div>
    </section>
  );
}
