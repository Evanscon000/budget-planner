import { describe, test, expect } from 'vitest';
import { render, screen } from '@testing-library/react';
import App from './App';

describe('App', () => {
    test('renders heading and button', () => {
        render(<App />);
        expect(screen.getByText("Vite + React")).toBeInTheDocument();
        expect(screen.getByRole("button", { name: /count/i })).toBeInTheDocument();
    });
});
